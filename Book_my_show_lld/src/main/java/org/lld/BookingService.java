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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class BookingService {
    ShowService showService;
    BookingRepo bookingRepo;
    LockProvider lockProvider;
    long TTL = 5000;
    // Permanently booked seats, keyed "showId:seatId" so booked state is per-show.
    Set<String> bookedSeatIds = ConcurrentHashMap.newKeySet();

    public BookingService(ShowService showService, BookingRepo bookingRepo, LockProvider lockProvider) {
        this.showService = showService;
        this.bookingRepo = bookingRepo;
        this.lockProvider = lockProvider;
    }

    public Booking createBooking(String userId, Show show, List<String> seatIds) {
        Map<String, Seat> seatMap = show.getScreen().getSeatMap();
        List<String> acquired = new ArrayList<>();   // locks grabbed in THIS attempt
        double totalAmount = 0;

        try {
            for (String seatId : seatIds) {
                Seat seat = seatMap.get(seatId);
                if (seat == null) {                                  // validate the seat exists
                    throw new SeatNotAvailableException("unknown seat id: " + seatId);
                }
                String key = show.getId() + ":" + seatId;
                // already sold, OR someone else currently holds the lock -> reject
                if (bookedSeatIds.contains(key) || !lockProvider.tryLock(key, userId, TTL)) {
                    throw new SeatNotAvailableException("seat not available with id: " + seatId);
                }
                acquired.add(key);
                totalAmount += seat.getPrice();
            }
        } catch (RuntimeException e) {
            // all-or-nothing: release every lock this attempt managed to grab
            for (String key : acquired) {
                lockProvider.unlock(key);
            }
            throw e;
        }

        Booking booking = new Booking(UUID.randomUUID().toString(), userId, seatIds,
                show.getId(), totalAmount, null, BookingStatus.CREATED);
        bookingRepo.save(booking);
        return booking;
    }

    public void confirmBooking(Booking booking, PaymentType paymentType) {
        if (booking.getBookingStatus() != BookingStatus.CREATED) {
            throw new IllegalStateException("Booking is not in a valid state for confirmation");
        }

        // holds must still be ours (isLockedBy already checks not-expired + owner)
        for (String seatId : booking.getSeatIds()) {
            String key = booking.getShowId() + ":" + seatId;
            if (!lockProvider.isLockedBy(key, booking.getUserId())) {
                throw new IllegalStateException("Seat lock expired or not owned by user");
            }
        }

        booking.setPaymentType(paymentType);
        PaymentStrategy strategy = PaymentStrategyFactory.getStrategy(paymentType);

        try {
            strategy.pay(booking.getAmount());
        } catch (RuntimeException e) {
            // payment failed: release the holds so the seats free up again
            for (String seatId : booking.getSeatIds()) {
                lockProvider.unlock(booking.getShowId() + ":" + seatId);
            }
            booking.setBookingStatus(BookingStatus.FAILED);
            throw e;
        }

        // success: mark seats permanently booked, THEN release the temporary holds
        for (String seatId : booking.getSeatIds()) {
            String key = booking.getShowId() + ":" + seatId;
            bookedSeatIds.add(key);
            lockProvider.unlock(key);
        }
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        System.out.println("Booking confirmed: " + booking.getBookingId());
    }
}
