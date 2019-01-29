package com.dalomao.thread.base;

/**
 * <p>Package: com.dalomao.demo.thread.base</p>
 * <p>Description:TODO </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/10
 **/
public class InterruptedDemo {
    class VisibilityTest extends Thread {
        public void run() {
            int i = 0;
            while(true) {
                //判断一下
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("interrupt");
                    System.out.println(i);
                    break;
                }
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        InterruptedDemo demo = new InterruptedDemo();
        VisibilityTest v = demo.new VisibilityTest();
        v.start();
        Thread.sleep(1000);
        v.interrupt();//中断
    }
}
