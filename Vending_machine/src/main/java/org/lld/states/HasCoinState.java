package org.lld.states;

import org.lld.VendingMachine;

public class HasCoinState implements State{
    private final VendingMachine vendingMachine;

    public HasCoinState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(int amt) {

    }

    @Override
    public void dispense() {
        System.out.println("product dispensed");
        int currentQyt= vendingMachine.getSelectedProduct().getQuantity();
        if(vendingMachine.getSelectedProduct().getQuantity()>0){
            vendingMachine.getSelectedProduct().setQuantity(currentQyt-1);
            // sold out
            if(vendingMachine.getSelectedProduct().getQuantity()==0){
                vendingMachine.getProducts().remove(vendingMachine.getSelectedProduct().getId());
            }
        }

        vendingMachine.setCurrentState(vendingMachine.getDispenseState());

        vendingMachine.dispenseProduct();

    }

    @Override
    public void selectProduct(int productId) {

    }

    @Override
    public void refill() {

    }
}
