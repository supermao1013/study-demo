package com.dalomao.thread.concurrent;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Created by maohw on 2018/12/11.
 * 许可信号量，实现有界缓存队列
 */
public class SemaphoreBufferDemo<T> {
    private final Semaphore items;//有多少元素可拿
    private final Semaphore space;//有多少空位可放元素
    private List queue = new LinkedList<>();

    public SemaphoreBufferDemo(int itemCounts){
        this.items = new Semaphore(0);
        this.space = new Semaphore(itemCounts);
    }

    //放入数据
    public void put(T x) throws InterruptedException {
        space.acquire();//拿空位的许可，没有空位线程会在这个方法上阻塞
        synchronized (queue){
            queue.add(x);
        }
        items.release();//有元素了，可以释放一个拿元素的许可
    }

    //取数据
    public T take() throws InterruptedException {
        items.acquire();//拿元素的许可，没有元素线程会在这个方法上阻塞
        T t;
        synchronized (queue){
            t = (T)queue.remove(0);
        }
        space.release();//有空位了，可以释放一个存在空位的许可

        return t;
    }

    public static void main(String[] args) throws InterruptedException {
        SemaphoreBufferDemo demo = new SemaphoreBufferDemo<String>(5);
        for (int i=0; i<10; i++) {
            demo.put(i + "");
            System.out.println(demo.take());
        }
    }
}
