package org.lld.products;

public abstract class Product {
    int id;
    int price;
    int quantity;

    public Product(int id,int price, int quantity) {
        this.id=id;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
