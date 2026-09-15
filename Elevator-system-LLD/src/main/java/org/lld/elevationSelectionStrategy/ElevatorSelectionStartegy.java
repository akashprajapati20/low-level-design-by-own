package org.lld.elevationSelectionStrategy;

import org.lld.ElevatorCar;
import org.lld.buttons.HallRequest;

import java.util.List;

public interface ElevatorSelectionStartegy {
    public ElevatorCar selectElevator(List<ElevatorCar> elevators, HallRequest request) ;


    }
