package com.dalomao.thread.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by maohw on 2018/12/11.
 * 许可信号量，共享锁
 * 可以设置允许多个线程同时访问临界区，比如设置10个许可信号量，允许10个线程同时进入（当然一个线程允许拿多个许可），但是第11个线程就必须等待了
 */
public class SemaphoreDemo implements Runnable {
    //定义5个信号量
    final Semaphore semp = new Semaphore(5);

    @Override
    public void run() {
        try {
            //获取一个信号量
            semp.acquire();
            //semp.acquire(2);//允许取多个
            //模拟耗时操作
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId() + ":done!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //要释放
            semp.release();
            //semp.release(2);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //线程池，20个线程
        ExecutorService exec = Executors.newFixedThreadPool(20);
        final  SemaphoreDemo demo = new SemaphoreDemo();
        for (int i=0; i<20; i++) {
            exec.submit(demo);
        }

        exec.shutdown();
    }

}
