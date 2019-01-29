package com.dalomao.thread.pool.custompool;

import java.util.Random;

/**
 * Created by maohw on 2019/1/29.
 */
public class CustomThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        // 创建3个线程的线程池
        CustomThreadPool t = new CustomThreadPool(3);
        t.execute(new MyTask("testA"));
        t.execute(new MyTask("testB"));
        t.execute(new MyTask("testC"));
        t.execute(new MyTask("testD"));
        t.execute(new MyTask("testE"));

        System.out.println(t);
        Thread.sleep(3000);
        t.destroy();// 所有线程都执行完成才destory

        System.out.println(t);
    }

    // 任务类
    static class MyTask implements Runnable {

        private String name;
        private Random r = new Random();

        public MyTask(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public void run() {// 执行任务
            try {
                Thread.sleep(r.nextInt(1000)+2000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getId()+" sleep InterruptedException:"
                        +Thread.currentThread().isInterrupted());
            }
            System.out.println("任务 " + name + " 完成");
        }
    }
}
