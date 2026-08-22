package org.lld.states;

import org.lld.products.Product;

public interface State {

    void insertCoin(int amt);
    void selectProduct(int product);
    void dispense();

    void refill();

}
