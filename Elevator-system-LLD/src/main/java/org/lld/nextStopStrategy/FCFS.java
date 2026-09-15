package org.lld.nextStopStrategy;

import org.lld.ElevatorCar;

import java.util.Iterator;

public class FCFS implements SchedulingStrategy{
    @Override
    public Integer nextStop(ElevatorCar elevatorCar) {
      Iterator<Integer>it= elevatorCar.getStops().iterator();
      if(it.hasNext()){
          return it.next();
      }
      return null;
    }
}
