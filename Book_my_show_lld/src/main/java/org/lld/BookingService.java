package org.lld;

import org.lld.enums.BookingStatus;
import org.lld.enums.PaymentType;
import org.lld.exceptions.SeatNotAvailableException;
import org.lld.locks.LockProvider;
import org.lld.models.Booking;
import org.lld.payments.PaymentStrategy;
import org.lld.payments.PaymentStrategyFactory;
import org.lld.repos.BookingRepo;
import org.lld.theatre.Seat;
import org.lld.theatre.Show;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class BookingService {
    ShowService showService;
    BookingRepo bookingRepo;
    LockProvider lockProvider;
    long TTL = 5000;
    Set<String> bookedSeatIds = ConcurrentHashMap.newKeySet();
    public BookingService(ShowService showService, BookingRepo bookingRepo, LockProvider lockProvider) {
        this.showService = showService;
        this.bookingRepo = bookingRepo;
        this.lockProvider = lockProvider;
    }

    public Booking createBooking(String userId, Show show, List<String> seatIds) {


        for (String seat : seatIds) {
            String key = show.getId() + ":" + seat;
            if (bookedSeatIds.contains(key) || !lockProvider.tryLock(key, userId, TTL)) {
                throw new SeatNotAvailableException("seat not available with id: " + seat);
            }
        }
            double totalAmount = 0;

            for (Seat s : show.getSeats()) {

                if (seatIds.contains(s.getId()) ) {
                    totalAmount += s.getPrice();
                }
            }

            Booking booking = new Booking(UUID.randomUUID().toString(), userId, seatIds, show.getId(), totalAmount, null, BookingStatus.CREATED);
            bookingRepo.save(booking);

            return booking;


    }

    public void confirmBooking(Booking booking, PaymentType paymentType){
        if(booking.getBookingStatus()!=BookingStatus.CREATED){
            throw new IllegalStateException( "Booking is not in a valid state for confirmation");
        }

        for (String seatId : booking.getSeatIds()) {
            String key = booking.getShowId() + ":" + seatId;

            if (lockProvider.isLockExpired(key)
                    || !lockProvider.isLockedBy(key, booking.getUserId())) {
                throw new IllegalStateException("Seat lock expired or not owned by user");
            }
        }

        booking.setPaymentType(paymentType);

        PaymentStrategy strategy =
                PaymentStrategyFactory.getStrategy(booking.getPaymentType());

        strategy.pay(booking.getAmount());

        for (String seatId : booking.getSeatIds()) {
            String key = booking.getShowId() + ":" + seatId;
            bookedSeatIds.add(key);
            lockProvider.unlock(key);

        }

        booking.setBookingStatus(BookingStatus.CONFIRMED);

        System.out.println("Booking confirmed: " + booking.getBookingId());
    }
}