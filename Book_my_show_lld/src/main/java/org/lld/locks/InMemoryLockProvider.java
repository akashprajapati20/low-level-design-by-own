package org.lld.locks;

import java.util.concurrent.*;

public class InMemoryLockProvider implements LockProvider{
    //If a nested class doesn't need access to the outer class instance, make it static.
    //The static here means:
    //
    //Expiry does not need an instance of InMemoryLockProvider to exist.
   private static class Expiry{
       private long deadline;
       private String owner;

       public Expiry(long deadline, String owner) {
           this.deadline = deadline;
           this.owner = owner;
       }
   }
    private  final ConcurrentHashMap<String,Expiry> locks=new ConcurrentHashMap<>();
   private final ScheduledExecutorService sweeper = Executors.newSingleThreadScheduledExecutor(r -> {
       Thread t = new Thread(r, "lock-sweeper");
       t.setDaemon(true);   // daemon so the JVM can exit when main finishes
       return t;
   });

   public InMemoryLockProvider(){
       sweeper.scheduleAtFixedRate(this::sweep,1,1, TimeUnit.MINUTES);

   }

   private void sweep(){
       long now= System.currentTimeMillis();
       locks.entrySet().removeIf(e->e.getValue().deadline<=now);
   }
    @Override
    public boolean tryLock(String key, String userId, long ttlMs) {
        long now= System.currentTimeMillis();
       Expiry expiry=new Expiry(ttlMs+now,userId);
      return locks.compute(key,(k,v)->(v==null || v.deadline<= now) ? expiry: v)==expiry;
    }

    @Override
    public void unlock(String key) {
locks.remove(key);
    }

    @Override
    public boolean isLockExpired(String key) {
       Expiry expiry=locks.get(key);
       return expiry!=null && expiry.deadline<=System.currentTimeMillis();
    }

    @Override
    public boolean isLockedBy(String key, String userId) {
        Expiry expiry=locks.get(key);
        return expiry!=null &&  expiry.deadline > System.currentTimeMillis() && userId.equals(expiry.owner)  ;
    }
}
