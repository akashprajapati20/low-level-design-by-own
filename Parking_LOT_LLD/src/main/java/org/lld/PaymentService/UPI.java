package org.lld.PaymentService;

public class UPI implements PaymentStrategy{
    public UPI() {
    }

    @Override
    public void payAmount(double amt) {
        System.out.println("payed by UPI : "+ amt);
    }
}
