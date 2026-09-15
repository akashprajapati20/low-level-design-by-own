package org.lld.buttons;

import org.lld.Direction;
import org.lld.ElevatorSystem;

public class HallButton implements Button{
    Direction direction;
    int floor;
    ElevatorSystem elevatorSystem;

    public HallButton( int floor, Direction direction,ElevatorSystem elevatorSystem) {
        this.direction = direction;
        this.floor = floor;
        this.elevatorSystem = elevatorSystem;
    }

    @Override
    public Request press() {
        HallRequest request = new HallRequest(floor, direction);
        elevatorSystem.handleHallRequest(request);
        return request;
    }
}
