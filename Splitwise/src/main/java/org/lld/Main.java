package org.lld;

import org.lld.models.Group;
import org.lld.models.Transaction;
import org.lld.models.User;
import org.lld.services.BalancesheetService;
import org.lld.services.ExpenseService;
import org.lld.services.SimplifyDebtsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        User ankit = new User("Ankit", "u1");
        User sahin = new User("sahin", "u2");
        User anuj = new User("anuj", "u3");

        Group g1 = new Group("Goa trip", 1);
        g1.addMember(ankit);
        g1.addMember(sahin);
        g1.addMember(anuj);

        ExpenseService expenseService = new ExpenseService();

        // Equal split: sahin pays 900 for all three -> 300 each
        expenseService.addExpense(sahin, "beer party", 900, SplitTypes.EQUAL, List.of(ankit, anuj, sahin), g1, null);

        // Percentage split: anuj pays 1000, ankit 50% / sahin 50%
        Map<User, Double> pct = new HashMap<>();
        pct.put(ankit, 50.0);
        pct.put(sahin, 50.0);
        expenseService.addExpense(anuj, "cab", 1000, SplitTypes.PERCENTAGE, List.of(ankit, sahin), g1, pct);

        BalancesheetService balancesheetService = new BalancesheetService();
        System.out.println("---- Current balances ----");
        balancesheetService.showBalances(g1);

        System.out.println("\n---- Simplified settlement plan ----");
        SimplifyDebtsService simplifyDebtsService = new SimplifyDebtsService();
        for (Transaction t : simplifyDebtsService.simplifyDebts(g1)) {
            System.out.println(t);
        }
    }
}
