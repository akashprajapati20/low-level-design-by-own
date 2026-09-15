package org.lld.nextStopStrategy;

import org.lld.Direction;
import org.lld.ElevatorCar;

public class LookSchedulingStrategy implements SchedulingStrategy{
    @Override
    public Integer nextStop(ElevatorCar elevator) {
        if (elevator.getStops().isEmpty()) {
            return null;
        }

        int floor = elevator.getCurrentFloor();
        Direction direction = elevator.getDirection();

        Integer nearestAbove = null; // smallest stop >= floor
        Integer nearestBelow = null; // largest stop  <= floor
        for (int stop : elevator.getStops()) {
            if (stop >= floor && (nearestAbove == null || stop < nearestAbove)) {
                nearestAbove = stop;
            }
            if (stop <= floor && (nearestBelow == null || stop > nearestBelow)) {
                nearestBelow = stop;
            }
        }

        return switch (direction) {
            case UP -> nearestAbove != null ? nearestAbove : nearestBelow;
            case DOWN -> nearestBelow != null ? nearestBelow : nearestAbove;
            case IDLE -> chooseNearest(floor, nearestAbove, nearestBelow);
        };
    }

    private Integer chooseNearest(int floor, Integer above, Integer below) {
        if (above == null) {
            return below;
        }
        if (below == null) {
            return above;
        }
        return (above - floor) <= (floor - below) ? above : below;
    }
}
