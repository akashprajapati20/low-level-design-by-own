package org.lld;

import org.lld.buttons.HallRequest;
import org.lld.buttons.Request;
import org.lld.elevationSelectionStrategy.ElevatorSelectionStartegy;

import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem {
    List<ElevatorCar>elevatorCars;
    ElevatorSelectionStartegy elevatorSelectionStartegy;


    public ElevatorSystem(List<ElevatorCar> elevatorCars, ElevatorSelectionStartegy elevatorSelectionStartegy) {
        this.elevatorCars = elevatorCars;
        this.elevatorSelectionStartegy = elevatorSelectionStartegy;
    }

    public void handleHallRequest(HallRequest request){
      ElevatorCar elevator=  elevatorSelectionStartegy.selectElevator(elevatorCars,request);
      elevator.addStops(request.getFloor());
        System.out.printf("Dispatcher assigned %s to elevator %d%n", request, elevator.getId());
    }

    public boolean hasPendingWork() {
        return elevatorCars.stream().anyMatch(ElevatorCar::hasPendingStops);
    }

    public void step() {
        for (ElevatorCar elevator : elevatorCars) {
            elevator.step();
        }
    }
}
