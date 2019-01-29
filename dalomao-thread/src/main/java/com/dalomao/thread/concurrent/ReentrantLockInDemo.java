package com.dalomao.thread.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by maohw on 2018/12/11.
 * ReentrantLock 支持可重入
 * 使用场景：在递归的时候使用，如果在递归的时候不支持可重入功能，那么可能会发生死锁
 */
public class ReentrantLockInDemo implements Runnable{
    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    @Override
    public void run() {
        for (int j=0; j<10000; j++) {
            //加两重锁
            lock.lock();
            lock.lock();
            try {
                i++;
            } finally {
                //在finally中解锁，必须放在finally中，防止异常发生也能执行
                //也要释放两次
                lock.unlock();
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockInDemo demo = new ReentrantLockInDemo();
        Thread t1 = new Thread(demo);
        Thread t2 = new Thread(demo);
        t1.start();t2.start();
        t1.join();t2.join();
        System.out.println(i);
    }

}
