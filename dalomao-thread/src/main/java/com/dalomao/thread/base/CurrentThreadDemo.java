package com.dalomao.thread.base;

/**
 * <p>Package: com.dalomao.demo.thread.base</p>
 * <p>Description:TODO </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2019/1/24
 **/
public class CurrentThreadDemo extends Thread{
    public CurrentThreadDemo() {
        System.out.println("construct begin");
        System.out.println("Thread.currentThread().getName()=" + Thread.currentThread().getName());
        System.out.println("this.getName()=" + this.getName());
        System.out.println("construct end");
    }

    @Override
    public void run() {
        System.out.println("run begin");
        System.out.println("Thread.currentThread().getName()=" + Thread.currentThread().getName());
        System.out.println("this.getName()=" + this.getName());
        System.out.println("run end");
    }

    public static void main(String[] args) {
        CurrentThreadDemo d = new CurrentThreadDemo();
        Thread t1 = new Thread(d);
        t1.setName("A");
        t1.start();
    }
}
