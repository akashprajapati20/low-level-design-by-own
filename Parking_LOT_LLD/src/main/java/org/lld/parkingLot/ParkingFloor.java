package org.lld.parkingLot;

import java.util.List;

public class ParkingFloor {
    List<ParkingSpot> parkingSpotsList;

    public ParkingFloor(List<ParkingSpot> parkingSpotsList) {
        this.parkingSpotsList = parkingSpotsList;
    }

    public ParkingSpot findNextAvailableSpot(SpotSize spotSize) {

        for(ParkingSpot spot:parkingSpotsList){
            if(spot.isSpotAvailable() && spot.getSpotSize()==spotSize){

                return spot;
            }
        }
        System.out.println("no available spot");
        return null;
    }
}
