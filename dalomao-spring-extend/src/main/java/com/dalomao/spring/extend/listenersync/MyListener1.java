package com.dalomao.spring.extend.listenersync;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 定义事件的观察者，即监听器，等待发布事件
 */
public class MyListener1 implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof MyEvent) {
            MyEvent event = (MyEvent) applicationEvent;
            System.out.println("Spring扩展测试：MyListener1 --> MyEvent param1=" + event.getParam1() + ",param2=" + event.getParam2() + "source=" + event.getSource());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("监听器1结束");
        }
    }
}
