package com.dalomao.spring.extend.listenerasync;

import org.springframework.context.ApplicationEvent;

/**
 * Created by maohw on 2018/11/2.
 */
public class AsyncEvent extends ApplicationEvent {
    private String param1;

    public AsyncEvent(Object source, String param1) {
        super(source);
        this.param1 = param1;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }
}
