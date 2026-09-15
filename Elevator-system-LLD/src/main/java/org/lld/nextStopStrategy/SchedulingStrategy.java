package org.lld.nextStopStrategy;

import org.lld.ElevatorCar;

public interface SchedulingStrategy {
    Integer nextStop(ElevatorCar elevatorCar);
}
