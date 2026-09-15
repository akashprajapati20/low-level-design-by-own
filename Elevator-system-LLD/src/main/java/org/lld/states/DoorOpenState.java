package org.lld.states;

import org.lld.Direction;
import org.lld.ElevatorCar;

public class DoorOpenState implements ElevatorState{

    @Override
    public void step(ElevatorCar elevator) {
        // Service the stop at the current floor: open, let people in/out, close.
        elevator.getDoor().open();
        elevator.removeStop(elevator.getCurrentFloor());
        elevator.getDoor().close();

        Integer next = elevator.getSchedulingStrategy().nextStop(elevator);
        if (next == null) {
            elevator.setDirection(Direction.IDLE);
            elevator.setState(new IdleState());
        } else if (next > elevator.getCurrentFloor()) {
            elevator.setDirection(Direction.UP);
            elevator.setState(new MovingUpState());
        } else if (next < elevator.getCurrentFloor()) {
            elevator.setDirection(Direction.DOWN);
            elevator.setState(new MovingDownState());
        } else {
            // another request for this same floor arrived; serve it next tick
            elevator.setState(new DoorOpenState());
        }

    }

    @Override
    public Direction getDirection() {
        return null;
    }

    @Override
    public String name() {
        return "";
    }
}
