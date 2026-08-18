package org.lld.PaymentService;

public class Payment {
  private final PaymentStrategy paymentStrategy;

    public Payment(PaymentStrategy paymentStrategy){
        this.paymentStrategy=paymentStrategy;
    }

   public  void pay(double amt){
        paymentStrategy.payAmount(amt);
    }
}
