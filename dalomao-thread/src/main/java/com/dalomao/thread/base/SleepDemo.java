package com.dalomao.thread.base;

import java.io.IOException;

/**
 * Created by maohw on 2018/12/10.
 * Thread.sleep不会让线程释放锁，也就是说如果当前线程持有对某个对象的锁（synchronized），其他线程也无法访问这个对象的
 */
public class SleepDemo {
    private int i = 0;
    private Object object = new Object();

    public static void main(String[] args) throws IOException {
        SleepDemo sleepDemo = new SleepDemo();
        MyThread thread1 = sleepDemo.new MyThread();
        MyThread thread2 = sleepDemo.new MyThread();
        thread1.start();
        thread2.start();
    }

    class MyThread extends Thread{
        @Override
        public void run() {
            synchronized (object) {
                i++;
                System.out.println("i:"+i);
                try {
                    System.out.println("线程"+Thread.currentThread().getName()+"进入睡眠状态");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    // TODO: handle exception
                }
                System.out.println("线程"+Thread.currentThread().getName()+"睡眠结束");
                i++;
                System.out.println("i:"+i);
            }
        }
    }
}
