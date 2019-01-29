package com.dalomao.thread.base;

/**
 * Created by maohw on 2018/12/10.
 */
public class IsAliveDemo {

    public static void main(String[] args) throws InterruptedException {
        IsAliveDemo isAliveDemo = new IsAliveDemo();
        MyThread myThread = isAliveDemo.new MyThread();
        System.out.println("begin == "+myThread.isAlive());
        myThread.start();
        System.out.println("end == "+myThread.isAlive());
    }

    class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("run == "+this.isAlive());
        }
    }
}
