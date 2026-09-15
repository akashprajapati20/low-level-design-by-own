package org.lld.models;

public class Door {
    DoorState doorState=DoorState.CLOSED;
   public Door(){

   }

    public DoorState getDoorState() {
        return doorState;
    }

    public void setDoorState(DoorState doorState) {
        this.doorState = doorState;
    }
    public  void open(){
        this.doorState= DoorState.OPEN;
    }
    public  void close(){
        this.doorState= DoorState.CLOSED;
    }
}
