package org.lld.states;

import org.lld.Direction;
import org.lld.ElevatorCar;

public class MovingUpState implements ElevatorState{
    @Override
    public void step(ElevatorCar elevator) {
        elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);
        if (elevator.getStops().contains(elevator.getCurrentFloor())) {
            elevator.setState(new DoorOpenState());
        }
    }

    @Override
    public Direction getDirection() {
        return Direction.UP;
    }

    @Override
    public String name() {
        return "MOVING_UP";
    }
}
