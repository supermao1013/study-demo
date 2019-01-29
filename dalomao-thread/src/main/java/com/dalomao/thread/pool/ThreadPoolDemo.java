package com.dalomao.thread.pool;

import java.util.Random;
import java.util.concurrent.*;

/**
 * <p>Package: com.dalomao.demo.thread.pool</p>
 * <p>Description:线程池使用的demo </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2019/1/28
 **/
public class ThreadPoolDemo {
    static class MyTask implements Runnable {

        private String name;


        public MyTask(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public void run() {// 执行任务
            try {
                Random r = new Random();
                Thread.sleep(r.nextInt(1000)+2000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getId()+" sleep InterruptedException:"
                        +Thread.currentThread().isInterrupted());
            }
            System.out.println("任务 " + name + " 完成");
        }
    }

    public static void main(String[] args) {
        //创建线程池
        ThreadPoolExecutor threadPool1 = new ThreadPoolExecutor(2,4,60, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(10));
        ExecutorService threadPool2 = Executors.newFixedThreadPool(2);
        ExecutorService threadPool3 = Executors.newSingleThreadExecutor();
        ExecutorService threadPool4 = Executors.newCachedThreadPool();
        ExecutorService threadPool5 = Executors.newWorkStealingPool();
        for(int i =0;i<=5;i++){
            MyTask task = new MyTask("Task_"+i);
            System.out.println("A new task will add:"+task.getName());
            threadPool1.execute(task);

        }
        threadPool1.shutdown();
        System.out.println("shutdown");
    }
}
