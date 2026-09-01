package org.lld;

import java.util.List;

public class Expense {
    User paidBy;
    String desription;
    double amount;
    List<Split> splits;
    SplitTypes splitTypes;

    public Expense(User paidBy, String desc, double amount, SplitTypes splitTypes,List<Split> splits){
        this.paidBy=paidBy;
        this.desription=desc;
        this.amount=amount;
        this.splitTypes=splitTypes;
        this.splits=splits;
    }

}
