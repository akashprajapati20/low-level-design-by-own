package org.lld;

import org.lld.buttons.ElevatorCarButton;
import org.lld.buttons.HallButton;
import org.lld.elevationSelectionStrategy.ElevatorSelectionStartegy;
import org.lld.elevationSelectionStrategy.NearestElevatorStrategy;
import org.lld.nextStopStrategy.LookSchedulingStrategy;
import org.lld.nextStopStrategy.SchedulingStrategy;
import org.lld.observer.Display;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        SchedulingStrategy scheduling = new LookSchedulingStrategy();

        ElevatorCar e1 = new ElevatorCar(1, 0, scheduling);
        ElevatorCar e2 = new ElevatorCar(2, 5, scheduling);

        // Observers watch each car.
        e1.addObserver(new Display("Lobby"));
        e2.addObserver(new Display("Lobby"));

        // The dispatcher decides which car serves a hall request.
        ElevatorSelectionStartegy selection = new NearestElevatorStrategy();
        ElevatorSystem system = new ElevatorSystem(List.of(e1, e2), selection);

        // --- Hall requests (external) flow through the dispatcher ---
        new HallButton(1, Direction.UP, system).press();    // e1 (floor 0) is nearest -> e1
        new HallButton(6, Direction.DOWN, system).press();  // e2 (floor 5) is nearest -> e2

        // --- Car requests (internal) belong to one specific elevator ---
        new ElevatorCarButton(4, e1).press(); // rider in e1 wants floor 4
        new ElevatorCarButton(8, e2).press(); // rider in e2 wants floor 8

        // Run the simulation until every car is idle.
        int tick = 0;
        while (system.hasPendingWork() && tick < 30) {
            System.out.println("--- tick " + (++tick) + " ---");
            system.step();
        }

        System.out.println("All requests served in " + tick + " ticks.");
    }
}