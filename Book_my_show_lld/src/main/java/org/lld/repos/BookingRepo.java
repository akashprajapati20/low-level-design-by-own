package org.lld.repos;

import org.lld.models.Booking;

import java.util.HashMap;
import java.util.Map;

public class BookingRepo {
    Map<String, Booking>bookingMap=new HashMap<>();

   public void save(Booking b){
        bookingMap.put(b.getBookingId(),b);

    }

    public Booking get(String id){
      return  bookingMap.get(id);
    }
}
