package org.lld;

import org.lld.products.Product;
import org.lld.states.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachine {
    State currentState;
Product selectedProduct=null;
NoCoinState noCoinState;
HasCoinState hasCoinState;
SelectedProduct selectedProductState;
DispenseState dispenseState;
int balance=0;

    Map<Integer,Product>products;

    public VendingMachine() {
        this.noCoinState = new NoCoinState(this);
        this.hasCoinState = new HasCoinState(this);
        this.selectedProductState = new SelectedProduct(this);
        this.dispenseState = new DispenseState(this);
        currentState=noCoinState;
        products=new HashMap<>();
    }
public void addProduct(Product p){
        products.put(p.getId(),p);
}
    void insertCoin(int amt){
        currentState.insertCoin(amt);
    }

    public State getCurrentState() {
        return currentState;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setCurrentState(State state){
        this.currentState=state;
    }

   public  void setSelectedProduct(Product p){
        selectedProduct=p;
    }

    public void selectProduct(int productId) {
        currentState.selectProduct(productId);

    }

    public Map<Integer, Product> getProducts() {
        return products;
    }

    public void processChangeAmt(){
        int change=this.balance-this.selectedProduct.getPrice();
System.out.println("processing your changes "+change);
    }

    public void dispenseProduct(){
        currentState.dispense();
    }
    public NoCoinState getNoCoinState() {
        return noCoinState;
    }

    public HasCoinState getHasCoinState() {
        return hasCoinState;
    }

    public SelectedProduct getSelectedProductState() {
        return selectedProductState;
    }

    public DispenseState getDispenseState() {
        return dispenseState;
    }

    public void addBalance(int amt){
        this.balance+=amt;
    }
public void setBalance(int amt){
        balance=amt;
}
    public int getBalance() {
        return balance;
    }

    public void confirmProduct(){
        currentState.dispense();
    }


}
