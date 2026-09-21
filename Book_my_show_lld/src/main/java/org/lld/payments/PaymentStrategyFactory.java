package org.lld.payments;

import org.lld.enums.PaymentType;

public class PaymentStrategyFactory {

  public  static PaymentStrategy getStrategy(PaymentType paymentType){
      return  switch (paymentType){
            case UPI -> new UPIPayment();
            case DEBIT -> new DebitCardPayment();

        };
    }
}
