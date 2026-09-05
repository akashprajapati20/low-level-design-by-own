package org.lld.services;

import org.lld.SplitTypes;
import org.lld.models.Expense;
import org.lld.models.Group;
import org.lld.models.Split;
import org.lld.models.User;
import org.lld.splitStrategy.SplitFactory;
import org.lld.splitStrategy.SplitStrategy;

import java.util.List;

public class ExpenseService {
BalancesheetService balancesheetService=new BalancesheetService();


    public void  addExpense(User paidBy, String desc, double amount, SplitTypes splitTypes, List<User>users,Group group){
       SplitStrategy splitStrategy= SplitFactory.getSplitStrategy(splitTypes);
        List<Split>splits= splitStrategy.getSplits(amount,users,null);
        Expense expense1=new Expense(paidBy,desc,amount,splitTypes,splits);
        group.addExpense(expense1);
        balancesheetService.updateBalances(paidBy,splits,group);


    }
}
