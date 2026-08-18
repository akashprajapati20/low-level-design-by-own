package org.lld.PaymentService;

public class DebitCard implements PaymentStrategy{

    @Override
    public void payAmount(double amt) {
      System.out.println("payed by Debit card : "+ amt);
    }
}
