package org.lld.parkingLot;

import org.lld.vehicle.Vehicle;

import java.sql.SQLOutput;

import static org.lld.parkingLot.SpotAvaliability.AVAILIABLE;
import static org.lld.parkingLot.SpotAvaliability.OCCUPIED;

public class ParkingSpot {
    int spotId;
    SpotSize spotSize;
    SpotAvaliability spotAvaliability=AVAILIABLE;
    Vehicle vehicle=null;


    public ParkingSpot(int spotId, SpotSize spotSize) {
        this.spotId = spotId;
        this.spotSize = spotSize;
    }

    public boolean isSpotAvailable(){
        return this.spotAvaliability == AVAILIABLE;
    }

    public SpotSize getSpotSize() {
        return spotSize;
    }

    public Vehicle getVehicle() {
        if(vehicle!=null){
            System.out.println(" vehicle is parked in this spot {}"+spotId);
        }
        return vehicle;
    }

    public void parkVehicle(Vehicle v){
        this.vehicle=v;
        this.spotAvaliability=OCCUPIED;
    }
    public void vacate(){
        this.spotAvaliability=AVAILIABLE;
    }
}
