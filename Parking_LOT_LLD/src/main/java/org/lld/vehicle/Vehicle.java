package org.lld.vehicle;

import org.lld.parkingLot.SpotSize;

public abstract class Vehicle {
    String numberPlate;
    SpotSize vehileSize;

    public Vehicle(String numberPlate, SpotSize vehileSize) {
        this.numberPlate = numberPlate;
        this.vehileSize = vehileSize;
    }

    public SpotSize getVehileSize() {
        return vehileSize;
    }


    public abstract  double calculateFee(double hours);
}
