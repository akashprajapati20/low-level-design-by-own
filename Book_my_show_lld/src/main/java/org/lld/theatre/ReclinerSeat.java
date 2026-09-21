package org.lld.theatre;

import org.lld.enums.SeatType;

public class ReclinerSeat extends Seat{
    public ReclinerSeat(String id, double price) {
        super(id, price);
    }

    @Override
    public SeatType getType() {
        return SeatType.RECLINER;
    }
}
