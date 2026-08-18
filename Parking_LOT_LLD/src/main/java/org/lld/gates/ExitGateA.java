package org.lld.gates;

import org.lld.PaymentService.Payment;
import org.lld.PaymentService.PaymentService;
import org.lld.PaymentService.PaymentStrategy;
import org.lld.Ticket;
import org.lld.parkingLot.ParkingSpot;
import org.lld.vehicle.Vehicle;

import java.time.Duration;
import java.time.LocalDateTime;

public class ExitGateA implements ExitGate{

    @Override
    public double processTicket(Ticket ticket) {
        LocalDateTime entryTime = ticket.getEntryTime();
        LocalDateTime exitTime= LocalDateTime.now();
        Duration duration = Duration.between(entryTime, exitTime);
        long minutes = duration.toMinutes();
        long hours = (long) Math.ceil(minutes / 60.0);

        Vehicle vehicle= ticket.getVehicle();
        ParkingSpot spot=ticket.getParkingSpot();
        spot.vacate();


        double amt= vehicle.calculateFee(hours);
       return amt;

    }
}
