package org.lld.buttons;

public class ElevatorCarRequest implements Request{
    int destinationFloor;

    public ElevatorCarRequest(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    @Override
    public int getFloor() {
        return destinationFloor;
    }
}
