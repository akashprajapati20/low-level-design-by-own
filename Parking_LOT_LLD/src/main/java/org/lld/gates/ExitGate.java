package org.lld.gates;

import org.lld.Ticket;
import org.lld.vehicle.Vehicle;

public interface ExitGate {
    public void processPayment(Ticket ticket);
}
