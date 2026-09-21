package org.lld;

import org.lld.enums.PaymentType;
import org.lld.locks.InMemoryLockProvider;
import org.lld.locks.LockProvider;
import org.lld.models.Booking;
import org.lld.models.Movie;
import org.lld.repos.BookingRepo;
import org.lld.repos.MovieRepository;
import org.lld.repos.ShowRepository;
import org.lld.repos.TheatreRepository;
import org.lld.theatre.*;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
// repos
        TheatreRepository theatreRepo = new TheatreRepository();
        MovieRepository movieRepo = new MovieRepository();
        ShowRepository showRepo = new ShowRepository();
        BookingRepo bookingRepo = new BookingRepo();

        // lock provider
        LockProvider lockProvider = new InMemoryLockProvider();

        // services
        TheatreService theatreService = new TheatreService(theatreRepo);
        MovieService movieService = new MovieService(movieRepo);
        ShowService showService = new ShowService(showRepo,  movieRepo ,theatreRepo);
        BookingService bookingService =
                new BookingService(showService,  bookingRepo,lockProvider);

        // Create theatre and screen
        Theatre pvr = theatreService.createTheatre("t1", "PVR Phoenix");

        Screen screen1 = new Screen("s1");
        theatreService.addScreen("t1", screen1);

        theatreService.addSeat(pvr.getId(), screen1.getId(), new RegularSeat("s1-2",100));
        theatreService.addSeat(pvr.getId(), screen1.getId(), new RegularSeat("s1-3",100));
        theatreService.addSeat(pvr.getId(), screen1.getId(), new RegularSeat("s1-4",100));
        theatreService.addSeat(pvr.getId(), screen1.getId(), new RegularSeat("s1-5",100));
        theatreService.addSeat(pvr.getId(), screen1.getId(), new RegularSeat("s1-6",100));
        theatreService.addSeat(pvr.getId(), screen1.getId(), new RegularSeat("s1-7",100));

        theatreService.addSeat(pvr.getId(), screen1.getId(), new ReclinerSeat("s1-8",100));
        theatreService.addSeat(pvr.getId(), screen1.getId(), new ReclinerSeat("s1-9",100));

        Movie movie=new Movie("m1","Interstellar",125);

        LocalDateTime showStartTime =
                LocalDateTime.of(2026, 7, 17, 18, 30);


        Show show1=showService.createShow("show1",movie,pvr.getId(), screen1.getId(), showStartTime);

        System.out.println("=====DEMO 1: search shows for movie.====");
        List<Show> shows=showService.getShowsByMovieTitle("Interstellar");
        shows.forEach(System.out::println);

        System.out.println("=====DEMO 2: 1 user books seat.====");
        Booking booking1=bookingService.createBooking("user1",show1,List.of("s1-2","s1-3"));
        bookingService.confirmBooking(booking1, PaymentType.UPI);


        System.out.println("=====DEMO 3: 2 user books seat.====");

        ExecutorService executorService= Executors.newFixedThreadPool(2);
        executorService.submit(()->{

            try {
                Booking booking2=bookingService.createBooking("user2",show1,List.of("s1-4","s1-5"));
                Thread.sleep(1000);
                bookingService.confirmBooking(booking2, PaymentType.UPI);
            } catch (Exception e) {
                System.out.println("user2 failed: "+e.getMessage());
            }


        });

        executorService.submit(()->{

            try {
                Booking booking2=bookingService.createBooking("user3",show1,List.of("s1-5","s1-6"));
                Thread.sleep(1000);
                bookingService.confirmBooking(booking2, PaymentType.UPI);
            } catch (Exception e) {
                System.out.println("user3 failed: "+e.getMessage());
            }


        });
executorService.shutdown();
executorService.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("=====DEMO 4: Booking expires after TTL.====");
        try{
            Booking b=bookingService.createBooking("user4",show1,List.of("s1-7","s1-8"));
            System.out.println("User 4 created a booking but did not pay.");
            Thread.sleep(6000);
            System.out.println("User 5 trying to book same seats after TTL");
            Booking b2=bookingService.createBooking("user5",show1,List.of("s1-7","s1-8"));
            System.out.println("User 5 created a booking");

            try{
                System.out.println("User 4 trying to pay after TTL");
                bookingService.confirmBooking(b,PaymentType.DEBIT);

            } catch (Exception e) {
                System.out.println("User 4 payment failed"+ e.getMessage());
            }

            try{
                System.out.println("User 5 trying to pay after TTL");
                bookingService.confirmBooking(b2,PaymentType.DEBIT);
                System.out.println("User 5 payment successful");
            } catch (Exception e) {
                System.out.println("User 5 payment failed"+ e.getMessage());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}