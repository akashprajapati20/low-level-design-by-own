package org.lld.states;

import org.lld.VendingMachine;
import org.lld.products.Product;

public class NoCoinState implements State{
    private final VendingMachine vendingMachine;

    public NoCoinState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(int amt) {

    }

    @Override
    public void dispense() {

    }

    @Override
    public void selectProduct(int productId) {
       Product p=vendingMachine.getProducts().getOrDefault(productId,null);
       if(p!=null){
           vendingMachine.setSelectedProduct(p);
           vendingMachine.setCurrentState(vendingMachine.getSelectedProductState());
       }else{
           System.out.println("please select a product");
       }

    }

    @Override
    public void refill() {

    }
}
