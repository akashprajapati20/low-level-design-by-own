package org.lld.elevationSelectionStrategy;

import org.lld.ElevatorCar;
import org.lld.buttons.HallRequest;

import java.util.Comparator;
import java.util.List;

public class LeastBusyStrategy implements ElevatorSelectionStartegy{
    @Override
    public ElevatorCar selectElevator(List<ElevatorCar> elevators, HallRequest request) {
       return elevators.stream().min(Comparator.comparingInt(e->e.getStops().size()))
               .orElseThrow(() -> new IllegalStateException("No elevators available"));
    }
}
