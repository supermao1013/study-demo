package com.dalomao.thread.concurrent;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Created by maohw on 2018/12/11.
 */
public class DeadLockCheck {

    private final static ThreadMXBean mbean = ManagementFactory.getThreadMXBean();

    final static Runnable deadlockCheck = new Runnable() {
        @Override
        public void run() {
            while (true) {
                //获取死锁的线程
                long[] deadlockedThreadIds = mbean.findDeadlockedThreads();

                if (deadlockedThreadIds != null) {
                    ThreadInfo[] threadInfos = mbean.getThreadInfo(deadlockedThreadIds);
                    for (Thread t : Thread.getAllStackTraces().keySet()) {
                        int length = threadInfos.length;
                        for (int i=0; i<length; i++) {
                            if (t.getId() == threadInfos[i].getThreadId()) {
                                //中断死锁的线程
                                t.interrupt();
                            }
                        }
                    }
                }

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public static void check() {
        Thread t = new Thread(deadlockCheck);
        t.setDaemon(true);
        t.start();
    }

}
