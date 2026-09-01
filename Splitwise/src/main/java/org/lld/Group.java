package org.lld;

import java.util.*;

public class Group {
    private final  String name;

    int groupId;
    private final List<User> members= new ArrayList<>();
    private final  List<Expense> expenses= new ArrayList<>();
    Map<User,BalanceSheet> balanceSheetMap=new HashMap<>();

    public Group(String name, int groupId) {
        this.name = name;
        this.groupId = groupId;
    }

    void addExpense(Expense expense){
        expenses.add(expense);
    }


}
