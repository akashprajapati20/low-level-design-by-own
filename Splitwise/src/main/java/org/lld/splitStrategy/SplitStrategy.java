package org.lld.splitStrategy;

import org.lld.Split;
import org.lld.User;

import java.util.List;

public interface SplitStrategy {
    List<Split> getSplits(int amt, List<User>users);
}
