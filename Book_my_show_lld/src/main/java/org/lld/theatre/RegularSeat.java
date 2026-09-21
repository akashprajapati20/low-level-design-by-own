package org.lld.theatre;

import org.lld.enums.SeatType;

public class RegularSeat extends Seat{
    public RegularSeat(String id, double price) {
        super(id, price);
    }

    @Override
    public SeatType getType() {
        return SeatType.REGULAR;
    }
}
