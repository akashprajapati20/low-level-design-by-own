package org.lld.gates;

import org.lld.Ticket;
import org.lld.vehicle.Vehicle;

public interface EntryGate {
    public Ticket processTicket(Vehicle V);
}
