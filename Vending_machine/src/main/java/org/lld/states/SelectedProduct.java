package org.lld.states;

import org.lld.VendingMachine;

public class SelectedProduct implements State{
    private final VendingMachine vendingMachine;

    public SelectedProduct(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(int amt){
     int selectedProductPrice=vendingMachine.getSelectedProduct().getPrice();
     vendingMachine.addBalance(amt);
     if(vendingMachine.getBalance()<selectedProductPrice){
         System.out.println("not sufficent amount inserted");
         vendingMachine.setCurrentState(vendingMachine.getNoCoinState());
     }else{
         vendingMachine.processChangeAmt();
         vendingMachine.setCurrentState(vendingMachine.getHasCoinState());

     }

    }

    @Override
    public void dispense() {

    }

    @Override
    public void selectProduct(int productId) {

    }

    @Override
    public void refill() {

    }
}
