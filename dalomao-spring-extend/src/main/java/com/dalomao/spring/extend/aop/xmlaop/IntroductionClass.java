package com.dalomao.spring.extend.aop.xmlaop;

public class IntroductionClass implements IntroductionIntf {
    
    public void extend() {
        System.out.println("我是切面Introduction引入的方法");
    }
}
