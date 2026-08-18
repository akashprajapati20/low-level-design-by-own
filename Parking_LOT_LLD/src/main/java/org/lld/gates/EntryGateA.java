package org.lld.gates;

import org.lld.Ticket;
import org.lld.parkingLot.ParkingLot;
import org.lld.parkingLot.ParkingSpot;
import org.lld.vehicle.Vehicle;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public class EntryGateA implements EntryGate {
    ParkingLot parkingLot;

    public EntryGateA(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public Ticket processTicket(Vehicle V) {
        ParkingSpot spot=parkingLot.findNextAvailableSpot(V.getVehileSize());
        if(spot!=null){
            Ticket ticket=new Ticket(UUID.randomUUID().toString(),LocalDateTime.now(),V,spot);
            spot.parkVehicle(V);
            return ticket;
        }else{
            System.out.println("parking is full");
        }
      return null;
    }
}
