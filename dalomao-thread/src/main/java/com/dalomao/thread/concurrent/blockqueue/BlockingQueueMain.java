package com.dalomao.thread.concurrent.blockqueue;

/**
 * Created by maohw on 2018/12/12.
 * 有界队列测试
 */
public class BlockingQueueMain {
    public static void main(String[] args) {
        BlockingQueueLockConditionDemo<Integer> bq = new BlockingQueueLockConditionDemo(10);
        Thread threadA = new ThreadPush(bq);
        threadA.setName("Push");
        Thread threadB = new ThreadPop(bq);
        threadB.setName("Pop");
        threadB.start();
        threadA.start();
    }

    private static class ThreadPush extends Thread{
        BlockingQueueLockConditionDemo<Integer> bq;

        public ThreadPush(BlockingQueueLockConditionDemo<Integer> bq) {
            this.bq = bq;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            int i = 20;
            while(i > 0){
                try {
                    Thread.sleep(500);
                    System.out.println(" i="+i+" will push");
                    bq.enqueue(i--);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }

            }
        }
    }

    private static class ThreadPop extends Thread{
        BlockingQueueLockConditionDemo<Integer> bq;

        public ThreadPop(BlockingQueueLockConditionDemo<Integer> bq) {
            this.bq = bq;
        }
        @Override
        public void run() {
            while(true){
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName()+" will pop.....");
                    Integer i = bq.dequeue();
                    System.out.println(" i="+i.intValue()+" alread pop");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
