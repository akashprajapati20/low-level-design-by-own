package org.lld.models;

import org.lld.enums.BookingStatus;
import org.lld.enums.PaymentType;
import org.lld.theatre.Seat;
import org.lld.theatre.Show;

import java.util.List;

public class Booking {
     String bookingId;
    String userId;
    List<String>seatIds;
    String showId;
    BookingStatus bookingStatus;
    double amount;
     PaymentType paymentType ;

    public Booking(String bookingId, String userId, List<String> seatIds, String showId, double amount, PaymentType paymentType, BookingStatus bookingStatus) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.seatIds = seatIds;
        this.showId = showId;
        this.amount = amount;
        this.paymentType = paymentType;
        this.bookingStatus = bookingStatus;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getBookingId() {
        return bookingId;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public String getUserId() {
        return userId;
    }

    public List<String> getSeatIds() {
        return seatIds;
    }

    public String getShowId() {
        return showId;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }
}
