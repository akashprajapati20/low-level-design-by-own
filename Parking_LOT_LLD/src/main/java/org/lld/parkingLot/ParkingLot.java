package org.lld.parkingLot;

import java.util.List;

public class ParkingLot {
    List<ParkingFloor>parkingFloorsList;

    public ParkingLot(List<ParkingFloor> parkingFloorsList) {
        this.parkingFloorsList = parkingFloorsList;
    }

    public ParkingSpot findNextAvailableSpot(SpotSize spotSize){
        ParkingSpot spot =null;
        for(ParkingFloor parking:parkingFloorsList){
            spot= parking.findNextAvailableSpot(spotSize);
            if(spot!=null)return spot;
        }
        return null;
    }
}
