package com.dalomao.thread.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by maohw on 2018/12/11.
 * ReadWriteLock
 * 读-读不互斥：读读之间不阻塞
 * 读-写互斥：读阻塞写，写也会阻塞读，保持数据一致性
 * 写-写互斥：写写阻塞
 */
public class ReadWriteLockDemo {
    static final Map<String,String> map = new HashMap<>();
    static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    static Lock r = reentrantReadWriteLock.readLock();
    static Lock w = reentrantReadWriteLock.writeLock();

    //写锁
    public void put(){
        w.lock();
        try{
            // do my work.....
        }finally{
            w.unlock();
        }
    }

    //读锁
    public void get(){
        r.lock();
        try{
            // do my work.....
        }finally{
            r.unlock();
        }
    }
}
