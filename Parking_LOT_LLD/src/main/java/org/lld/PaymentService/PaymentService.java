package org.lld.PaymentService;

import java.util.Scanner;

public class PaymentService {
    public Payment createPayment(PaymentMethod method) {   // choice comes IN as a parameter
        switch (method) {
            case UPI:  return new Payment(new UPI());
            case CARD: return new Payment(new DebitCard());
            default:   throw new IllegalArgumentException("Unknown method: " + method);
        }
    }
}
