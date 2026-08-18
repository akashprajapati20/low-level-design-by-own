package org.lld.PaymentService;

import java.util.Scanner;

public class PaymentService {

    public Payment processPayment(){
        System.out.println("Chooose the payment method : 1--> UPI and   2--> Credit card");
        Scanner sc=new Scanner(System.in);
        int a= sc.nextInt();

//        1--> UPI
//                2--> Credit card
        switch(a){
            case 1:
                return new Payment(new UPI());
            case 2:
                return new Payment(new DebitCard());
            default:
                return null;
        }

    }

}
