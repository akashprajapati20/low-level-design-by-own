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

    public void showBalances(Group group){
        boolean anyDebt = false;
        for(User user : group.getUsers()){
            Map<User, Double> balances = group.getBalanceSheetMap(user).getBalances();
            for(Map.Entry<User, Double> entry : balances.entrySet()){
                // print each debt once, from the debtor's side (negative balance)
                if(entry.getValue() < 0){
                    System.out.println(user.getName() + " owes " + entry.getKey().getName() + " : " + (-entry.getValue()));
                    anyDebt = true;
                }
            }
        }
        if(!anyDebt){
            System.out.println("All settled up!");
        }
    }
}
