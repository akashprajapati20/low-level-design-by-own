package org.lld.vehicle;

import org.lld.parkingLot.SpotSize;

public class Motorcycle extends Vehicle{
    double RATE=5.0;

    public Motorcycle(String numberPlate, SpotSize vehileSize) {
        super(numberPlate,vehileSize);
    }

    @Override
    public  double calculateFee(double hours) {
        return RATE*hours;
    }
}
