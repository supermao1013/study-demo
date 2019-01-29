package com.dalomao.thread.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by maohw on 2018/12/11.
 * ReentrantLock 支持可中断
 *
 * 可以加中断锁，lockInterruptibly()替代lock()，lockInterruptibly()会响应中断事件，lock()是不响应中断事件的。
 * 适用于在很容易发生死锁的情况，检查死锁然后中断线程，然后捕获到中断通知退出线程，避免死锁
 */
public class ReentrantLockInterruptDemo implements Runnable{
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    public int lock = 0;
    public ReentrantLockInterruptDemo(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            //构造死锁现象
            if (lock == 1) {
                //中断锁
                lock1.lockInterruptibly();
                Thread.sleep(500);
                lock2.lockInterruptibly();
            } else {
                lock2.lockInterruptibly();
                Thread.sleep(500);
                lock1.lockInterruptibly();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread()) {
                //若该锁被锁住了，则解锁
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getId() + "：线程退出");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockInterruptDemo r1 = new ReentrantLockInterruptDemo(1);
        ReentrantLockInterruptDemo r2 = new ReentrantLockInterruptDemo(2);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();t2.start();
        Thread.sleep(1000);

        //中断其中一个线程
        DeadLockCheck.check();
    }
}
