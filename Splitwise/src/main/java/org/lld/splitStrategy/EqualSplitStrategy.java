package org.lld.splitStrategy;

import org.lld.Split;
import org.lld.User;

import java.util.List;

public class EqualSplitStrategy implements SplitStrategy{
    @Override
    public List<Split> getSplits(int amt, List<User> users) {
        return List.of();
    }
}
