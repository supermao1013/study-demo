package com.dalomao.thread.base;

import java.io.IOException;

/**
 * 调用yield方法会交出CPU，让CPU去执行其他线程。和sleep方法类似，不会释放锁
 * 调用yield方法并不会让线程进入阻塞状态，而是让线程重新回到runnable状态，它只需要等待重新获得CPU执行时间，因此有可能下一秒又开始执行
 */
public class YieldDemo {
    private int i = 0;
    private Object object = new Object();

    public static void main(String[] args) throws IOException {
        YieldDemo yieldDemo = new YieldDemo();
        MyThread thread1 = yieldDemo.new MyThread();
        thread1.start();

    }

    class MyThread extends Thread{
        @Override
        public void run() {
            long beginTime=System.currentTimeMillis();
            int count=0;
            for (int i=0;i<50000000;i++){
                count = count+(i+1);
                //Thread.yield();
            }
            long endTime=System.currentTimeMillis();
            System.out.println("用时："+(endTime-beginTime)+" 毫秒！");
        }
    }
}
