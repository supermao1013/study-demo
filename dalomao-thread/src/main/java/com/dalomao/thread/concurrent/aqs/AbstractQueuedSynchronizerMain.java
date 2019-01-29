package com.dalomao.thread.concurrent.aqs;

import java.util.concurrent.locks.Lock;

/**
 * Created by maohw on 2018/12/12.
 */
public class AbstractQueuedSynchronizerMain {

    public void test() {
        final Lock lock = new AbstractQueuedSynchronizerDemo();
        class Worker extends Thread {
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // 启动10个子线程
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }

        // 主线程每隔1秒换行
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        AbstractQueuedSynchronizerMain testMyLock = new AbstractQueuedSynchronizerMain();
        testMyLock.test();
    }
}
