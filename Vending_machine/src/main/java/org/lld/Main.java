package org.lld;

import org.lld.products.Frooti;
import org.lld.products.Product;
import org.lld.products.choclate;
import org.lld.states.DispenseState;
import org.lld.states.HasCoinState;
import org.lld.states.NoCoinState;
import org.lld.states.SelectedProduct;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) {

        Product frooti=new Frooti(1,10,2);
        Product choc=new choclate(2,20,2);


        VendingMachine vendingMachine=new VendingMachine();
        vendingMachine.addProduct(frooti);
        vendingMachine.addProduct(choc);

        vendingMachine.selectProduct(1);
        vendingMachine.insertCoin(2);
        vendingMachine.selectProduct(1);
        vendingMachine.insertCoin(10);
         vendingMachine.confirmProduct();

    }
}