package org.lld.states;

import org.lld.Direction;
import org.lld.ElevatorCar;

public interface ElevatorState {
    void step(ElevatorCar elevatorCar);
   Direction getDirection();
   String name();
}
