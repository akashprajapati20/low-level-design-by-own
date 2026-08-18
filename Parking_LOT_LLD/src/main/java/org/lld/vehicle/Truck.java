package org.lld.vehicle;

import org.lld.parkingLot.SpotSize;

public class Truck extends Vehicle{
    double RATE=10.0;

    public Truck(String numberPlate, SpotSize vehileSize) {
        super(numberPlate,vehileSize);
    }

    @Override
    public  double calculateFee(double hours) {
        return RATE*hours;
    }
}
