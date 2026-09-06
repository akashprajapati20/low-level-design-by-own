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
        if(Math.abs(totalPercent - 100) > 1e-6) throw new IllegalArgumentException("total percentage should be 100");

        List<Split>all_splits=new ArrayList<>();
        for(User u:participants){
            all_splits.add(new Split(u,totalAmount*metadata.getOrDefault(u,0.0)/100));
        }
        return all_splits;
    }
}
