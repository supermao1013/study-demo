package com.dalomao.spring.extend.aop.xmlaop;

public class MyServiceImpl implements MyService {
    
    public void execute() {
        System.out.println("MyServiceImpl execute执行");
        //        throw new RuntimeException();
    }
}
