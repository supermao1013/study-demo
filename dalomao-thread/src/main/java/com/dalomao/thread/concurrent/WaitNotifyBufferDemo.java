package com.dalomao.thread.concurrent;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 自定义队列，这里用原生的wait/notify会报错（因为wait和notify有执行先后顺序），只要知道原理即可。
 * 改成LockSupport的park和unpark就不会报错
 * @param <T>
 */
public class WaitNotifyBufferDemo<T> {
    private Object putObj = new Object();
    private Object takeObj = new Object();
    private Queue queue = new ArrayBlockingQueue<T>(10);


    public void put(T t) {
        synchronized (queue) {
            if (queue.size() >= 10) {
                try {
                    putObj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            queue.add(t);
            takeObj.notifyAll();
        }
    }

    public T take() {
        T t;
        synchronized (queue) {
            if (queue.size() <= 0) {
                try {
                    takeObj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            t = (T) queue.remove();
            putObj.notifyAll();
        }

        return t;
    }

    public static void main(String[] args) {
        WaitNotifyBufferDemo<Integer> demo = new WaitNotifyBufferDemo<>();
        for (int i=0; i<10; i++) {
            demo.put(i);
            System.out.println("取出：" + demo.take());
        }
    }
}
