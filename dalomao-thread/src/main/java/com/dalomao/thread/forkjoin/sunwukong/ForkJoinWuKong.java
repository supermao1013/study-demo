package com.dalomao.thread.forkjoin.sunwukong;


import com.dalomao.thread.forkjoin.MakePanTaoArray;
import com.dalomao.thread.forkjoin.service.IPickTaoZi;
import com.dalomao.thread.forkjoin.service.IProcessTaoZi;
import com.dalomao.thread.forkjoin.sunwukong.Impl.WuKongPickImpl;
import com.dalomao.thread.forkjoin.sunwukong.Impl.WuKongProcessImpl;
import com.dalomao.thread.forkjoin.vo.PanTao;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoinPool：将一个大任务拆分成多个小任务后，使用fork可以将小任务分发给其他线程同时处理，使用join可以将多个线程处理的结果进行汇总；这实际上就是分治思想的并行版本
 * ForkJoinPool并不会为每个子任务创建单独的线程。相反，池中每个线程都有自己的双端队列(Deque)用于存储任务。这个双端队列对于工作窃取算法至关重要
 * Fork/Join框架的核心思想：
 * 1.每个线程都有自己的一个WorkQueue，该工作队列是一个双端队列。
 * 2.队列支持三个功能push、pop、poll
 * 3.push/pop只能被队列的所有者线程调用，而poll可以被其他线程调用。
 * 4.划分的子任务调用fork时，都会被push到自己的队列中。
 * 5.默认情况下，工作线程从自己的双端队列获出任务并执行。
 * 6.当自己的队列为空时，线程随机从另一个线程的队列末尾调用poll方法窃取任务
 */
public class ForkJoinWuKong {

    /**
     * 我们要使用ForkJoin框架，必须首先创建一个ForkJoin任务。
     * 它提供在任务中执行fork()和join()操作的机制。通常情况下，我们不需要直接继承ForkJoinTask类，只需要继承它的子类，Fork/Join框架提供了以下两个子类。
     * 1.RecursiveAction：用于没有返回结果的任务
     * 2.RecursiveTask：用于有返回结果的任务
     *
     * ForkJoinTask有以下三个核心方法:
     * 1.fork()：在任务执行过程中将大任务划分为多个小的子任务，调用子任务的fork()方法可以将任务放到线程池中异步调度
     * 2.join()：调用子任务的join()方法等待任务返回的结果。这个方法类似于Thread.join()，区别在于前者不受线程中断机制的影响
     * 3.invoke()：在当前线程同步执行该任务。该方法也不受中断机制影响
     */
    private static class XiaoWuKong extends RecursiveTask<Integer> {
        private final static int THRESHOLD = 100;//阈值，数组大小，进行具体的业务操作
        private PanTao[] src;
        private int fromIndex;
        private int toIndex;
        private IPickTaoZi pickTaoZi;

        public XiaoWuKong(PanTao[] src, int fromIndex, int toIndex, IPickTaoZi pickTaoZi) {
            this.src = src;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
            this.pickTaoZi = pickTaoZi;
        }

        @Override
        protected Integer compute() {
            //数组长度小于阈值，直接排序
            if (toIndex-fromIndex<THRESHOLD){
                int count =0 ;
                for(int i=fromIndex;i<toIndex;i++){
                    if (pickTaoZi.pick(src,i)) count++;
                }

                return count;
            }else{
                //数组长度大于阈值，将数组平分为两份，由两个子任务进行排序
                //fromIndex....mid......toIndex
                int mid = (fromIndex+toIndex)/2;
                XiaoWuKong left = new XiaoWuKong(src,fromIndex,mid,pickTaoZi);
                XiaoWuKong right = new XiaoWuKong(src,mid,toIndex,pickTaoZi);
                //invokeAll:让第一个任务同步执行，其他任务异步执行(注意：其他任务先fork，第一个任务再invoke)
                invokeAll(left,right);

                //调用子任务的join()方法等待任务返回的结果
                return left.join() + right.join();
            }
        }
    }

    public static void main(String[] args) {
        //ForkJoinTask需要通过ForkJoinPool来执行
        //Fork/Join有同步和异步两种方式
        ForkJoinPool pool = new ForkJoinPool();

        PanTao[] src = MakePanTaoArray.makeArray();
        IProcessTaoZi processTaoZi = new WuKongProcessImpl();
        IPickTaoZi pickTaoZi = new WuKongPickImpl(processTaoZi);

        long start = System.currentTimeMillis();

        //invoke:同步执行
        XiaoWuKong xiaoWuKong = new XiaoWuKong(src, 0, src.length-1, pickTaoZi);
        pool.invoke(xiaoWuKong);

        System.out.println("The count is " + xiaoWuKong.join() + " spend time:" + (System.currentTimeMillis()-start) + "ms");
    }

}
