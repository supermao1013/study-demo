package com.dalomao.thread.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by maohw on 2018/12/11.
 * 循环栅栏。类似于CountDownLatch，但是CountDownLatch执行一次就结束，而CyclicBarrier可以反复循环执行。
 * 比如，假设我们将计数器设置为10，那么筹齐第一批10个线程后，计数器就会归零，然后接着凑齐下一批10个线程
 */
public class CyclicBarrierDemo {

    static class Writer extends Thread {
        private CyclicBarrier cyclicBarrier;
        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程"+Thread.currentThread().getName()+"正在写入数据...");
            try {
                Thread.sleep(5000);      //以睡眠来模拟写入数据操作
                System.out.println("线程"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕");
                cyclicBarrier.await();//全部线程都到达这一个屏障后，继续往下执行
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("所有线程写入完毕，继续处理其他任务...");
        }
    }

    public static void main(String[] args) {
        int n = 4;

        //四个线程都执行完毕后，退出
        CyclicBarrier barrier  = new CyclicBarrier(n);

        //在所有线程写入操作完之后，进行额外的其他操作可以为CyclicBarrier提供Runnable参数
        //会从四个线程中选择一个线程去执行Runnable
        //这里的Runnable执行完毕之后，四个线程才会顺利全部退出
        /*CyclicBarrier barrier  = new CyclicBarrier(n, new Runnable() {
            @Override
            public void run() {
                System.out.println("所有线程处理完毕后，选择其中一个线程来处理接下来的任务，当前线程" + Thread.currentThread().getName());

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });*/

        //第一次使用
        for(int i=0; i<n; i++) {
            new Writer(barrier).start();
        }

        try {
            Thread.sleep(25000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //第二次可以继续使用，CountDownLatch则无法进行重复使用
        System.out.println("--------------------------------------------");
        System.out.println("CyclicBarrier第二次重用");
        for(int i=0; i<n; i++) {
            new Writer(barrier).start();
        }
    }
}
