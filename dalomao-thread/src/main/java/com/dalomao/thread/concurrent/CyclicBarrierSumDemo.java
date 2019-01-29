package com.dalomao.thread.concurrent;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by maohw on 2018/12/11.
 * 循环栅栏。
 * 统计每个线程计算后的结果
 */
public class CyclicBarrierSumDemo {

    static CyclicBarrier c = new CyclicBarrier(5,new SumThread());

    //子线程结果存放的缓存
    private static ConcurrentHashMap<String,Integer> resultMap = new ConcurrentHashMap<>();

    //所有子线程达到屏障后，会执行这个Runnable的任务
    private static class SumThread implements Runnable{
        @Override
        public void run() {
            int result =0;
            for(Map.Entry<String,Integer> workResult : resultMap.entrySet()){
                result = result+workResult.getValue();
            }
            System.out.println("result = "+result);
            System.out.println("在这里完全可以做与子线程，统计无关的事情.....");
        }
    }

    //工作线程，也就是子线程
    private static class WorkThread implements Runnable{

        private Random t = new Random();

        @Override
        public void run() {
            int r = t.nextInt(1000) + 1000;
            System.out.println(Thread.currentThread().getId() + ":r=" + r);
            resultMap.put(Thread.currentThread().getId() + "", r);
            try {
                Thread.sleep(1000+r);

                // 每个线程停在这里，当所有线程都到达这里后，继续往下执行。
                // 如果CyclicBarrier中有传入Runnable，则会先去执行Runnable，然后每个线程继续往下执行，所以一般c.await()一般放在子线程执行的最后
                c.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        for(int i=0; i<=4; i++){
            Thread thread = new Thread(new WorkThread());
            thread.start();
        }
    }
}
