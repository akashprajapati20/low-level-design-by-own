package org.lld.splitStrategy;

import org.lld.SplitTypes;

public class SplitFactory {

   public static SplitStrategy getSplitStrategy(SplitTypes splitTypes){
       switch (splitTypes){
           case EQUAL -> {
               return new EqualSplitStrategy();
           }
           case PERCENTAGE -> {
               return new PercentageSplitStrategy();
           }
           default -> throw new RuntimeException("not correct splitTypes");
       }
    }
}
