package com.dalomao.thread.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by maohw on 2018/12/11.
 * 倒数计时器
 * 一种典型的场景就是火箭发射。在火箭发射前，为了保证万无一失，往往还要进行各项设备、仪器的检查。只有等所有检查完毕后，引擎才能点火
 * 它可以使得点火线程，等待所有检查线程全部完工后，再执行
 */
public class CountDownLatchDemo implements Runnable {
    //定义10个检查量
    static final CountDownLatch end = new CountDownLatch(10);

    @Override
    public void run() {
        try {
            //模拟检查任务
            Thread.sleep(new Random().nextInt(10)*1000);
            System.out.println(Thread.currentThread().getId() + " check complete");
            //检查完，数量减掉
            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        final  CountDownLatchDemo demo = new CountDownLatchDemo();
        for (int i=0; i<10; i++) {
            exec.submit(demo);
        }
        //同步等待检查完毕
        end.await();

        //发射火箭
        System.out.println("Fire!");

        exec.shutdown();
    }

}
