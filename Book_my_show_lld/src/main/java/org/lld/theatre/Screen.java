package org.lld.theatre;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Screen {
    String id;
    Map<String,Seat>seatMap=new HashMap<>();

    public Screen(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Map<String, Seat> getSeatMap() {
        return seatMap;
    }

    public void addSeat(Seat seat){
        seatMap.put(seat.getId(),seat);
    }
}
