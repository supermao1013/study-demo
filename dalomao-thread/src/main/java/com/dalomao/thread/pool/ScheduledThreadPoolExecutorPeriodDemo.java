package com.dalomao.thread.pool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>Package: com.dalomao.demo.thread.pool</p>
 * <p>Description:ScheduledThreadPoolExecutor的周期执行测试 </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2019/1/28
 **/
public class ScheduledThreadPoolExecutorPeriodDemo implements Runnable {
    public static enum OperType{
        None,OnlyThrowException,CatheException
    }

    public static SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private OperType operType;

    public ScheduledThreadPoolExecutorPeriodDemo(OperType operType) {
        this.operType = operType;
    }

    @Override
    public void run() {
        switch (operType){
            case OnlyThrowException:
                System.out.println("Exception not catch:"+formater.format(new Date()));
                throw new RuntimeException("OnlyThrowException");
            case CatheException:
                try {
                    throw new RuntimeException("CatheException");
                } catch (RuntimeException e) {
                    System.out.println("Exception be catched:"+formater.format(new Date()));
                }
                break;
            case None:
                System.out.println("None :"+formater.format(new Date()));
        }
    }

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);

        /**
         * 每隔一段时间打印系统时间，互不影响的创建并执行一个在给定初始延迟后首次启用的定期操作，
         * 后续操作具有给定的周期；
         * 也就是将在 initialDelay 后开始执行，周期为period。
         */
        exec.scheduleAtFixedRate(new ScheduledThreadPoolExecutorPeriodDemo(
                ScheduledThreadPoolExecutorPeriodDemo.OperType.None),
                1000,5000, TimeUnit.MILLISECONDS);

        // 开始执行后就触发异常,next周期将不会运行
        exec.scheduleAtFixedRate(new ScheduledThreadPoolExecutorPeriodDemo(
                ScheduledThreadPoolExecutorPeriodDemo.OperType.OnlyThrowException),
                1000,5000, TimeUnit.MILLISECONDS);

        // 虽然抛出了运行异常,当被拦截了,next周期继续运行
        exec.scheduleAtFixedRate(new ScheduledThreadPoolExecutorPeriodDemo(
                ScheduledThreadPoolExecutorPeriodDemo.OperType.CatheException),
                1000,5000, TimeUnit.MILLISECONDS);

        /**
         * 创建并执行一个在给定初始延迟后首次启用的定期操作，
         * 随后，在每一次执行终止和下一次执行开始之间都存在给定的延迟。
         */
        exec.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("scheduleWithFixedDelay:begin"
                        + ScheduledThreadPoolExecutorPeriodDemo.formater.format(new Date()));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("scheduleWithFixedDelay:end"
                        + ScheduledThreadPoolExecutorPeriodDemo.formater.format(new Date()));
            }
        },1000,5000,TimeUnit.MILLISECONDS);

        /**
         * 创建并执行在给定延迟后启用的一次性操作。
         */
        exec.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("schedule running.....");
            }
        },5000,TimeUnit.MILLISECONDS);
    }
}
