package com.dalomao.thread.deadlock.bank.service;


import com.dalomao.thread.deadlock.bank.vo.Account;

/**
 * 安全的转账流程
 */
public class SafeTransfer implements ITransfer {

    private static Object tieLock = new Object();

    @Override
    public void transfer(Account from, Account to, int amount)
            throws InterruptedException {

        /**
         * 若A->B，B->A两种情况同时发生，则会根据这两个账户的hashcode进行排序
         * 然后根据排序情况按顺序获得锁，这样就可以避免发生死锁情况
         */

        //System.identityHashCode：忽略重写的hashCode，即即使重写了hashCode也不影响结果
        int fromHash = System.identityHashCode(from);
        int toHash = System.identityHashCode(to);

        if(fromHash<toHash){
            synchronized (from){
                System.out.println(Thread.currentThread().getName()+" get "+from.getName());
                Thread.sleep(100);
                synchronized (to){
                    System.out.println(Thread.currentThread().getName()
                            +" get "+to.getName());
                    from.flyMoney(amount);
                    to.addMoney(amount);
                    System.out.println(from);
                    System.out.println(to);
                }
            }
        }else if(toHash<fromHash){
            synchronized (to){
                System.out.println(Thread.currentThread().getName()+" get "+to.getName());
                Thread.sleep(100);
                synchronized (from){
                    System.out.println(Thread.currentThread().getName()
                            +" get "+from.getName());
                    from.flyMoney(amount);
                    to.addMoney(amount);
                    System.out.println(from);
                    System.out.println(to);
                }
            }
        }else{
            /**
             * 千万分之几的概率会导致两个不同对象的hashCode相同，因此这里再加另外一把锁保证
             */
            synchronized (tieLock){
                synchronized (to){
                    System.out.println(Thread.currentThread().getName()+" get "+from.getName());
                    Thread.sleep(100);
                    synchronized (from){
                        System.out.println(Thread.currentThread().getName()
                                +" get "+to.getName());
                        from.flyMoney(amount);
                        to.addMoney(amount);
                    }
                }
            }
        }
    }
}
