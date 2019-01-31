package com.dalomao.thread.deadlock.bank.vo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by maohw on 2019/1/31.
 * 账户
 */
public class Account {
    private long number;
    private final String name;
    private int money;
    private final Lock lock = new ReentrantLock();

    public Lock getLock() {
        return lock;
    }

    public Account(String name, int amount) {
        this.name = name;
        this.money = amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", money=" + money +
                '}';
    }

    public void addMoney(int amount){
        money = money + amount;
    }

    public void flyMoney(int amount){
        money = money - amount;
    }
}
