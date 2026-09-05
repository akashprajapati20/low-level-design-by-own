package org.lld.splitStrategy;

import org.lld.models.Group;
import org.lld.models.Split;
import org.lld.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EqualSplitStrategy implements SplitStrategy{
    @Override
    public List<Split> getSplits(double amt, List<User>users, Map<User, Double>meta) {

      List<Split>splits=new ArrayList<>();
      int total_users= users.size();
      for(User u:users){
          Split split=new Split(u,1.0* amt/total_users);
          splits.add(split);
      }



      return splits;
    }
}
