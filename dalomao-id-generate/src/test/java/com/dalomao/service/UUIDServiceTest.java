package com.dalomao.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.CountDownLatch;

/**
 * Created by maohw on 2019/1/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class UUIDServiceTest {

    private static final int threadNum = 1000;

    // 线程同步工具
    private static CountDownLatch cdl = new CountDownLatch(threadNum); // 100 -1 =99 - 98 --- =0

    @Autowired
    @Qualifier("uuidServiceRedisImpl")
    private IUUIDService uuidService;

    @Test
    public void test() throws Exception {
        System.out.println("============================test start");
        for (int i = 0; i < threadNum; i++) {
            new Thread(new orderThread()).start();
            cdl.countDown();// 计数， threadNum-1， =0时， 唤醒所有线程
        }
        Thread.currentThread();
        Thread.sleep(3000);
        System.out.println("============================test end");
    }

    class orderThread implements Runnable {
        public void run() {
            try {
                cdl.await(); // 等待线程同步执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(uuidService.generateId());
        }
    }
}
