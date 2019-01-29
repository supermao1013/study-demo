package com.dalomao.thread.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by maohw on 2018/12/11.
 * ReentrantLock
 */
public class ReentrantLockDemo implements Runnable{
    public static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        lock.lock();
        try {
            //you can do something here
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockDemo demo = new ReentrantLockDemo();
        Thread t1 = new Thread(demo);
        t1.start();
        t1.join();
    }

}
