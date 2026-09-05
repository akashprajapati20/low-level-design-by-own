package org.lld.services;

import org.lld.models.BalanceSheet;
import org.lld.models.Group;
import org.lld.models.Split;
import org.lld.models.User;

import java.util.List;
import java.util.Map;

public class BalancesheetService {

    public void updateBalances(User paidBy , List<Split>splits, Group group){
        double amount = splits.stream().mapToDouble(Split::getAmount).sum();
        group.getBalanceSheetMap(paidBy).addToTotalPaid(amount);

        for(Split split:splits){
            User user=split.getUser();
            double amt=split.getAmount();

            group.getBalanceSheetMap(user).addToTotalExpenditure(amt);
            if(!user.equals(paidBy)){
                group.getBalanceSheetMap(user).addToBalances(paidBy,-amt);
                group.getBalanceSheetMap(paidBy).addToBalances(user,amt);
            }
        }
    }
}
