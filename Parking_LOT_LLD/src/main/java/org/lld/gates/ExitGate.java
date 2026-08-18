package org.lld.gates;

import org.lld.Ticket;
import org.lld.vehicle.Vehicle;

public interface ExitGate {
    public double processTicket(Ticket ticket);
}
