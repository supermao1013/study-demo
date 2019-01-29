package com.dalomao.thread.concurrent;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by maohw on 2019/1/24.
 * LockSupport不需要在同步代码块里，线程间不需要维护一个共享的同步对象，实现线程间的解耦
 * unpark函数可以先于park调用，所以不需要担心线程间的执行的先后顺序
 *
 * 以上两点是LockSupport优于Object自带的wait和notify/notifyAll的地方
 */
public class LockSupportDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                int sum = 0;
                for (int i = 0; i<100; i++) {
                    sum += i;
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("执行完毕，进入阻塞状态：" + sum);
                LockSupport.park();

            }
        });

        threadA.start();
//        Thread.sleep(5000);
        System.out.println("调用unpark");
        LockSupport.unpark(threadA);
    }
}
