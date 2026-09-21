package org.lld.payments;

public class DebitCardPayment implements PaymentStrategy{
    @Override
    public void pay(double amt) {
        System.out.println("Paid by Debit Card: " + amt);
    }
}
