package org.lld.models;

import java.util.HashMap;
import java.util.Map;

public class BalanceSheet {

    private double totalExpenditure=0;
    private double totalPaid=0;
    private Map<User,Double> balances=new HashMap<>();

    public Map<User, Double> getBalances() {
        return balances;
    }

    public void addToTotalPaid(double amount){
        this.totalPaid+=amount;
    }
    public void addToTotalExpenditure(double amount){
        this.totalExpenditure+=amount;
    }

    public void addToBalances(User u,double amount){
        balances.put(u,balances.getOrDefault(u,0.0)+amount);
        if(Math.abs(balances.get(u))<1e-6){balances.remove(u);}
    }

    public void clearBalances(){
        balances.clear();
    }
}
