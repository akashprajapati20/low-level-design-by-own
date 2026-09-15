package org.lld;

import org.lld.building.Floor;
import org.lld.buttons.ElevatorCarRequest;
import org.lld.buttons.Request;
import org.lld.models.Door;
import org.lld.nextStopStrategy.SchedulingStrategy;
import org.lld.observer.Display;
import org.lld.observer.ElevatorObserver;
import org.lld.states.ElevatorState;
import org.lld.states.IdleState;

import java.util.*;

public class ElevatorCar {
    int id;
    Direction direction=Direction.IDLE;
    int currentFloor;
    Set<Integer>stops=new LinkedHashSet<>();
    Door door=new Door();
  ElevatorState state=new IdleState();
  SchedulingStrategy schedulingStrategy;
List<ElevatorObserver>observers=new ArrayList<>();
    public ElevatorCar(int id, int currentFloor, SchedulingStrategy schedulingStrategy) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.schedulingStrategy = schedulingStrategy;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public SchedulingStrategy getSchedulingStrategy() {
        return schedulingStrategy;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public Door getDoor() {
        return door;
    }

    public void setDoor(Door door) {
        this.door = door;
    }

    public ElevatorState getState() {
        return state;
    }

    public void setState(ElevatorState state) {
        this.state = state;
    }

    void step(){
        state.step(this);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getId() {
        return id;
    }
    public void handleCarRequest(ElevatorCarRequest request) {
        addStops(request.getFloor());
    }

    void handleCarRequest(Request request){

    }

   public void addStops(int i){
        stops.add(i);
    }
public void removeStop(Integer floor){
        stops.remove(floor);
}
    public Set<Integer> getStops() {
        return stops;
    }

    public void addObserver(ElevatorObserver observer) {
        observers.add(observer);
    }

    public boolean hasPendingStops() {
        return !stops.isEmpty();
    }
}
