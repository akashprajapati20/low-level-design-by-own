package org.lld.models;

import java.util.*;

public class Group {

    private final  String name;

    int groupId;
    private final List<User> members;
    private final  List<Expense> expenses;
    Map<User, BalanceSheet> balanceSheetMap=new HashMap<>();

    public Group(String name, int groupId) {
        this.name = name;
        this.groupId = groupId;
        this.members= new ArrayList<>();
        this.expenses= new ArrayList<>();
    }
    public void addMember(User e){
        BalanceSheet balanceSheet=new BalanceSheet();
        members.add(e);
        balanceSheetMap.put(e,balanceSheet);
    }
   public void addExpense(Expense expense){
        expenses.add(expense);
    }

    public List<User> getUsers(){
        return this.members;
    }

    public Map<User, BalanceSheet> getBalanceSheetMap() {
        return balanceSheetMap;
    }
    public BalanceSheet getBalanceSheetMap(User user) {
        return balanceSheetMap.get(user);
    }

}
