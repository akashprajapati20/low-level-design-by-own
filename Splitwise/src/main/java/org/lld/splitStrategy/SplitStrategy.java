package org.lld.splitStrategy;

import org.lld.models.Group;
import org.lld.models.Split;
import org.lld.models.User;

import java.util.List;
import java.util.Map;

public interface SplitStrategy {
    List<Split> getSplits(double amt, List<User>users, Map<User, Double> meta);
}
