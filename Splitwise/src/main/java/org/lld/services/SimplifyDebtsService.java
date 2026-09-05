package org.lld.services;

import org.lld.models.BalanceSheet;
import org.lld.models.Group;
import org.lld.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class SimplifyDebtsService {

   public void simplifyDebts(Group group){
       List<User> users=group.getUsers();
       Map<User, BalanceSheet> allBalanceSheets=group.getBalanceSheetMap();
       Map<User,Double>netBalances=new HashMap<>();
       for(User user:users){
        BalanceSheet balanceSheet= group.getBalanceSheetMap(user);
         double netBalance=0.0;

         for(double value:balanceSheet.getBalances().values()){
             netBalance+=value;
         }
           netBalances.put(user,netBalance);
         balanceSheet.clearBalances();
       }

       PriorityQueue<User>creditors=new PriorityQueue<>((a,b)->Double.compare(netBalances.get(b),netBalances.get(a) ));
       PriorityQueue<User>debtors=new PriorityQueue<>((a,b)->Double.compare(netBalances.get(a), netBalances.get(b) ));

       for(User user : users) {
           double net = netBalances.get(user);
           if (net > 0) {
               creditors.offer(user);
           } else if (net < 0) {
               debtors.offer(user);
           }
       }

       // Step 3: Match debtors and creditors to settle debts
       while (!creditors.isEmpty() && !debtors.isEmpty()) {
           User creditor = creditors.poll();
           User debtor = debtors.poll();
           double creditAmount = netBalances.get(creditor);
           double debitAmount = netBalances.get(debtor);
           double settledAmount = Math.min(creditAmount, -debitAmount);
           // Update balances both sides
           allBalanceSheets.get(creditor).addToBalances(debtor, settledAmount);
           allBalanceSheets.get(debtor).addToBalances(creditor, -settledAmount);

           netBalances.put(creditor, creditAmount - settledAmount);
           netBalances.put(debtor, debitAmount + settledAmount);

           //if still unsettle enter in queue again
           if(netBalances.get(creditor)>0){
               creditors.offer(creditor);
           }
           if(netBalances.get(debtor)<0){
               debtors.offer(debtor);
           }

       }
   }
}
