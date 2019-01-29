package com.dalomao.thread.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by maohw on 2018/12/11.
 * ReentrantLock 支持可限时
 * 在获取锁时，允许等待一定时间获取锁，如果时间到达仍未获取到锁则返回false，去执行其他操作，在一定程度上也能避免死锁
 */
public class ReentrantLockTimeLimitDemo implements Runnable{
    public static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getId() + " get lock success");
                Thread.sleep(6000);
            } else {
                System.out.println(Thread.currentThread().getId() + " get lock failed");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTimeLimitDemo demo = new ReentrantLockTimeLimitDemo();
        Thread t1 = new Thread(demo);
        Thread t2 = new Thread(demo);
        t1.start();t2.start();
    }
}
