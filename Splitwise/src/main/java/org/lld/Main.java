package org.lld;

import org.lld.models.Expense;
import org.lld.models.Group;
import org.lld.models.User;
import org.lld.services.ExpenseService;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        User ankit=new User("Ankit","u1");
        User sahin=new User("sahin","u2");
        User anuj=new User("anuj","u3");

        Group g1=new Group("Goa trip",1);
        g1.addMember(ankit);
        g1.addMember(sahin);
        g1.addMember(anuj);

        ExpenseService expenseService = new ExpenseService() ;
         expenseService.addExpense(sahin,"beer party",1000,SplitTypes.EQUAL, List.of(ankit,anuj,sahin),g1);



    }
}