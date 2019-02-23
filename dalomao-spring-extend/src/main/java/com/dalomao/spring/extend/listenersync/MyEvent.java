package com.dalomao.spring.extend.listenersync;

import org.springframework.context.ApplicationEvent;

/**
 * 定义事件
 */
public class MyEvent extends ApplicationEvent {
    public String param1;

    public String param2;

    /**
     * 构造函数，继承ApplicationEvent固定写法
     * @param source
     * @param param1
     * @param param2
     */
    public MyEvent(Object source,String param1,String param2) {
        //固定写法，一定要调用super(source)
        super(source);
        this.param1 = param1;
        this.param2 = param2;
    }

    public Object getSource() {
        return super.getSource();
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }
}
