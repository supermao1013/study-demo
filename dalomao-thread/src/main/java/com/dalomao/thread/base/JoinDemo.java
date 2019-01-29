package com.dalomao.thread.base;

/**
 * join会等待子线程处理完毕后，再执行主线程
 */
public class JoinDemo {

    public static void main(String[] args) throws InterruptedException {
        // 启动子进程
        JoinDemo joinDemo = new JoinDemo();
        joinDemo.new MyThread("new thread").start();

        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                MyThread th = joinDemo.new MyThread("joined thread");
                th.start();
//                th.join();
            }
            System.out.println(Thread.currentThread().getName() + "  " + i);
        }
    }

    class MyThread extends Thread{
        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println(getName() + "  " + i);
            }
        }
    }
}
