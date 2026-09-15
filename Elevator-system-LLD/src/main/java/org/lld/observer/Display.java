package org.lld.observer;

import org.lld.ElevatorCar;

public class Display implements ElevatorObserver{
    private final String name;

    public Display(String name) {
        this.name = name;
    }

    @Override
    public void update(ElevatorCar elevator) {
        System.out.printf("[%s] Elevator %d -> floor %d, direction %s%n",
                name, elevator.getId(), elevator.getCurrentFloor(), elevator.getDirection());
    }
}
