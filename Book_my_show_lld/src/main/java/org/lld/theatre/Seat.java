package org.lld.theatre;

import org.lld.enums.SeatStatus;
import org.lld.enums.SeatType;

public abstract class Seat {

  String id;
  double price;


    public Seat(String id, double price) {
        this.id = id;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public abstract SeatType getType();

    public double getPrice() {
        return price;
    }


}
