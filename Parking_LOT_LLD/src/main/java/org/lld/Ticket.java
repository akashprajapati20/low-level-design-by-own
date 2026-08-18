package org.lld;

import org.lld.parkingLot.ParkingSpot;
import org.lld.vehicle.Vehicle;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Ticket {
    String id;
    LocalDateTime entryTime;
    ParkingSpot parkingSpot;
    Vehicle vehicle;

    public Ticket(String id, LocalDateTime entryTime, Vehicle vehicle, ParkingSpot parkingSpot) {
        this.id = id;
        this.entryTime = entryTime;
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
