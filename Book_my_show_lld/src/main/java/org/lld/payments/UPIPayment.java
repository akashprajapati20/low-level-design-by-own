package org.lld.payments;

public class UPIPayment implements PaymentStrategy{
    @Override
    public void pay(double amt) {
        System.out.println("Paid by UPI: "+ amt);
    }
}
