package org.lld.buttons;

import org.lld.Direction;

public class HallRequest implements Request {

    int floor;
    Direction direction;

    public HallRequest(int floor, Direction direction) {
        this.floor = floor;
        this.direction = direction;
    }

    @Override
    public int getFloor() {
        return floor;
    }

    Direction getDirection(){
        return direction;
    }
}
