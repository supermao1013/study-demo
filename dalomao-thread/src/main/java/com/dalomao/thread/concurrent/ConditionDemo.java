package com.dalomao.thread.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by maohw on 2018/12/11.
 * 类似于Object.wait()和Object.notify()
 * 与ReentrantLock结合使用，实现等待/通知机制
 */
public class ConditionDemo implements Runnable {
    public static Lock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    @Override
    public void run() {
        try {
            lock.lock();
            //等待被唤醒的时候要重新获取锁
            System.out.println("Thread is await");
            //等待，并释放锁
            condition.await();
            System.out.println("Thread is going on");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionDemo demo = new ConditionDemo();
        Thread t1 = new Thread(demo);
        t1.start();
        Thread.sleep(2000);

        //通知线程t1继续执行
        lock.lock();
        //唤醒线程，但该线程必须等待锁释放后再重新获取锁才能继续往下执行
        condition.signal();
        System.out.println("已经通知唤醒线程");
        //condition.signalAll();//尽量少使用
        Thread.sleep(5000);
        lock.unlock();
    }

}
