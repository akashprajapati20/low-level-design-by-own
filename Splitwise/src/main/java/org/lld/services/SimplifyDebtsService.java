package org.lld.services;

import org.lld.models.Group;
import org.lld.models.Transaction;
import org.lld.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class SimplifyDebtsService {

    private static final double EPS = 1e-6;

    public List<Transaction> simplifyDebts(Group group) {
        List<User> users = group.getUsers();

        // Step 1: collapse each user's pairwise balances into a single net figure.
        Map<User, Double> netBalances = new HashMap<>();
        for (User user : users) {
            double netBalance = 0.0;
            for (double value : group.getBalanceSheetMap(user).getBalances().values()) {
                netBalance += value;
            }
            netBalances.put(user, netBalance);
        }

        // Step 2: two max-heaps — biggest creditor and biggest debtor on top.
        PriorityQueue<User> creditors = new PriorityQueue<>((a, b) -> Double.compare(netBalances.get(b), netBalances.get(a)));
        PriorityQueue<User> debtors = new PriorityQueue<>((a, b) -> Double.compare(netBalances.get(a), netBalances.get(b)));

        for (User user : users) {
            double net = netBalances.get(user);
            if (net > EPS) {
                creditors.offer(user);
            } else if (net < -EPS) {
                debtors.offer(user);
            }
        }

        // Step 3: greedily settle biggest against biggest, collecting the plan.
        List<Transaction> transactions = new ArrayList<>();
        while (!creditors.isEmpty() && !debtors.isEmpty()) {
            User creditor = creditors.poll();
            User debtor = debtors.poll();
            double creditAmount = netBalances.get(creditor);
            double debitAmount = netBalances.get(debtor);
            double settledAmount = Math.min(creditAmount, -debitAmount);

            transactions.add(new Transaction(debtor, creditor, settledAmount));

            netBalances.put(creditor, creditAmount - settledAmount);
            netBalances.put(debtor, debitAmount + settledAmount);

            if (netBalances.get(creditor) > EPS) {
                creditors.offer(creditor);
            }
            if (netBalances.get(debtor) < -EPS) {
                debtors.offer(debtor);
            }
        }

        return transactions;
    }
}
