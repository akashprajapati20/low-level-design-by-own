package org.lld.elevationSelectionStrategy;

import org.lld.ElevatorCar;
import org.lld.buttons.HallRequest;

import java.util.Comparator;
import java.util.List;

public class NearestElevatorStrategy implements ElevatorSelectionStartegy{
    @Override
    public ElevatorCar selectElevator(List<ElevatorCar> elevators, HallRequest request) {
        return elevators.stream()
                .min(Comparator.comparingInt(e -> Math.abs(e.getCurrentFloor() - request.getFloor())))
                .orElseThrow(() -> new IllegalStateException("No elevators available"));
    }
}
