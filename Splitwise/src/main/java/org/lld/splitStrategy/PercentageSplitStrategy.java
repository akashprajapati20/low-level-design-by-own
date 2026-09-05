package org.lld.splitStrategy;

import org.lld.models.Group;
import org.lld.models.Split;
import org.lld.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PercentageSplitStrategy implements SplitStrategy{
    @Override
    public List<Split> getSplits(double totalAmount, List<User>participants, Map<User, Double>metadata) {
        double totalPercent=metadata.values().stream().mapToDouble(Double::doubleValue).sum();
        if(totalPercent!=100)throw new IllegalArgumentException("total percenty should be 100");

        List<Split>all_splits=new ArrayList<>();
        for(User u:participants){
            all_splits.add(new Split(u,totalAmount*metadata.getOrDefault(u,0.0)/100));
        }
        return all_splits;
    }
}
