package com.dalomao.thread.concurrent.blockqueue;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by maohw on 2018/12/12.
 * Lock、Condition实现线程安全的有界队列
 */
public class BlockingQueueLockConditionDemo<T> {
    private List queue = new LinkedList<>();
    private final int limit;//队列最大大小
    Lock lock = new ReentrantLock();

    private Condition needNotEmpty = lock.newCondition();
    private Condition needNotFull = lock.newCondition();


    public BlockingQueueLockConditionDemo(int limit) {
        this.limit = limit;
    }

    /**
     * 入队
     * @param item
     * @throws InterruptedException
     */
    public void enqueue(T item) throws InterruptedException {
        lock.lock();
        try{
            while(this.queue.size()==this.limit){
                needNotFull.await();
            }
            this.queue.add(item);//将对象插入队列尾部，成功返回true，失败（没有空间）抛出异常IllegalStateException
            needNotEmpty.signal();
        }finally{
            lock.unlock();
        }
    }

    /**
     * 出队
     * @return
     * @throws InterruptedException
     */
    public  T dequeue() throws InterruptedException {
        lock.lock();
        try{
            while(this.queue.size()==0){
                needNotEmpty.await();
            }

            T t = (T) this.queue.remove(0);//获取并移除队列头部元素，如果队列为空，抛出NoSuchElementException异常
            needNotFull.signal();

            return t;
        }finally{
            lock.unlock();
        }
    }
}
