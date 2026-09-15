package org.lld.buttons;

import org.lld.ElevatorCar;
import org.lld.building.Floor;

public class ElevatorCarButton implements Button{
    private final int destinationFloor;
    private final ElevatorCar elevator;

    public ElevatorCarButton(int destinationFloor, ElevatorCar elevator) {
        this.destinationFloor = destinationFloor;
        this.elevator = elevator;
    }

    @Override
    public Request press() {
        ElevatorCarRequest request = new ElevatorCarRequest(destinationFloor);
        elevator.handleCarRequest(request);
        return request;
    }
}
