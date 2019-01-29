package com.dalomao.thread.pool.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * <p>Package: com.dalomao.demo.thread.pool.future</p>
 * <p>Description:TODO </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2019/1/28
 **/
public class FutureDemo {
    public static void main(String[] args) {
        FutureDemo futureSample = new FutureDemo();
        // 创建任务集合
        List<FutureTask<Integer>> taskList = new ArrayList<>();
        //另一种方式
        //List<Future<Integer>> futureList = new ArrayList<>();

        ExecutorService exec = Executors.newFixedThreadPool(5);
        for(int i=0;i<10;i++){
            // 传入Callable对象创建FutureTask对象，Callable有返回值适合用，而runable没有返回值不适合用
            FutureTask<Integer> ft = new FutureTask<Integer>(new ComputeTask(i,"task_"+i));
            taskList.add(ft);
            exec.submit(ft);
//            Future<Integer> result = exec.submit(new ComputeTask(i,"task_"+i));
//            futureList.add(result);
        }
        System.out.println("主线程已经提交任务，接下来可以做自己的事！");

        // 开始统计各计算线程计算结果
        int totalResult =0;
        for(FutureTask<Integer> ft : taskList){
            try {
                //FutureTask的get方法会自动阻塞,直到获取计算结果为止
                totalResult = totalResult + ft.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("total = "+totalResult);
        exec.shutdown();

    }
}
