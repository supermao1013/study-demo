package com.dalomao.spring.extend.listenerasync;

import com.dalomao.spring.extend.listenerasync.AsyncEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by maohw on 2018/11/2.
 */
@Component
public class AsyncListener1 implements ApplicationListener<AsyncEvent>  {

    @Async
    @Override
    public synchronized void onApplicationEvent(AsyncEvent asyncEvent) {
        System.out.println("异步监听器1接收到事件");
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("异步监听器1结束：" + asyncEvent.getParam1());
    }
}
