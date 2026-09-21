package org.lld.repos;

import org.lld.models.Booking;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BookingRepo {
    Map<String, Booking> bookingMap = new ConcurrentHashMap<>();

   public void save(Booking b){
        bookingMap.put(b.getBookingId(),b);

    }

    public Booking get(String id){
      return  bookingMap.get(id);
    }
}
