package org.lld;


import org.lld.PaymentService.Payment;
import org.lld.PaymentService.PaymentMethod;
import org.lld.PaymentService.PaymentService;
import org.lld.gates.EntryGate;
import org.lld.gates.EntryGateA;
import org.lld.gates.ExitGate;
import org.lld.gates.ExitGateA;
import org.lld.parkingLot.ParkingFloor;
import org.lld.parkingLot.ParkingLot;
import org.lld.parkingLot.ParkingSpot;
import org.lld.parkingLot.SpotSize;
import org.lld.vehicle.Car;
import org.lld.vehicle.Vehicle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
  Map<Vehicle,Ticket> ticketMap=new HashMap<>();
        Vehicle car=new Car("UK07", SpotSize.MEDIUM);
        ParkingSpot spot1=new ParkingSpot(1,SpotSize.LARGE);
        ParkingSpot spot2=new ParkingSpot(2,SpotSize.SMALL);
        ParkingSpot spot3=new ParkingSpot(3,SpotSize.LARGE);
        ParkingSpot spot4=new ParkingSpot(4,SpotSize.MEDIUM);

        List<ParkingSpot>parkingSpotList= List.of(spot1,spot2,spot3,spot4);

        ParkingFloor firstFloor=new ParkingFloor(parkingSpotList);

        ParkingLot parkingLot=new ParkingLot(List.of(firstFloor));

        EntryGate entryGate=new EntryGateA(parkingLot);


        Scanner sc = new Scanner(System.in);
        System.out.println("Choose: 1 -> UPI, 2 -> Card");
        int choice = sc.nextInt();

        PaymentMethod method = (choice == 1) ? PaymentMethod.UPI : PaymentMethod.CARD;
PaymentService paymentService=new PaymentService();
        Payment payment = paymentService.createPayment(method);   // decision handed IN


        ExitGate exitGate=new ExitGateA(paymentService);


       Ticket ticket= entryGate.processTicket(car);
       if(ticket!=null){
           ticketMap.put(ticket.getVehicle(),ticket);
       }
        spot1.getVehicle();
        spot2.getVehicle();
        spot3.getVehicle();
        spot4.getVehicle();

       double amountToPay= exitGate.processTicket(ticket);
        payment.pay(amountToPay);

    }
}