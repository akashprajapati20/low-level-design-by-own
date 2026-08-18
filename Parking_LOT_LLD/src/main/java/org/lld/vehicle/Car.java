package org.lld.vehicle;

import org.lld.parkingLot.SpotSize;

public class Car extends Vehicle{
    double RATE=10.0;

    public Car(String numberPlate, SpotSize vehileSize) {
        super(numberPlate,vehileSize);
    }

    @Override
    public double calculateFee(double hours) {
        return RATE*hours;
    }
}
