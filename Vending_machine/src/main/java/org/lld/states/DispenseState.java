package org.lld.states;

import org.lld.VendingMachine;

public class DispenseState implements State{
    private final VendingMachine vendingMachine;

    public DispenseState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(int amt) {

    }

    @Override
    public void dispense() {
        vendingMachine.setBalance(0);
        vendingMachine.setSelectedProduct(null);
        vendingMachine.setCurrentState(vendingMachine.getNoCoinState());
        System.out.println("done with the flow back to noCoin state");
    }

    @Override
    public void selectProduct(int productId)  {

    }

    @Override
    public void refill() {

    }


}
