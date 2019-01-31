package com.dalomao.thread.deadlock.bank.service;

import com.dalomao.thread.deadlock.bank.vo.Account;

/**
 * 正常流程的转账
 */
public class NormalTransfer implements ITransfer {
    @Override
    public void transfer(Account from, Account to, int amount)
            throws InterruptedException {
        //若A->B，B->A两种情况同时发生，则会发生死锁
        synchronized (from){
            System.out.println(Thread.currentThread().getName()+" get "+from.getName());
            Thread.sleep(100);
            synchronized (to){
                System.out.println(Thread.currentThread().getName()
                        +" get "+to.getName());
                from.flyMoney(amount);
                to.addMoney(amount);
            }
        }
    }
}
