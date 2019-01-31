package com.dalomao.thread.deadlock.bank.service;


import com.dalomao.thread.deadlock.bank.vo.Account;

/**
 * 转账接口
 */
public interface ITransfer {

    void transfer(Account from, Account to, int amount) throws InterruptedException;
}
