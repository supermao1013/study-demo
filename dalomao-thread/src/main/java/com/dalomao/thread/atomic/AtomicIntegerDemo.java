package com.dalomao.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by maohw on 2018/12/11.
 */
public class AtomicIntegerDemo {
    static AtomicInteger i = new AtomicInteger();

    public static class AddThread implements Runnable {
        @Override
        public void run() {
            for (int k=0; k<10000; k++) {
                i.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] ts = new Thread[10];
        for (int i=0; i<10; i++) {
            ts[i] = new Thread(new AddThread());
        }

        for (int i=0; i<10; i++) {
            ts[i].start();
        }

        for (int i=0; i<10; i++) {
            //等待子线程销毁
            ts[i].join();
        }

        System.out.println(i);
    }

}
