package com.dalomao.spring.extend.aop.xmlaop;

import org.aspectj.lang.ProceedingJoinPoint;

public class AopAspectj {
    
    public void before() {
        System.out.println("开启事务");
    }
    
    public void after() {
        System.out.println("关闭事务");
    }
    
    public void afterReturning() {
        System.out.println("返回后通知");
    }
    
    public void afterthrowing() {
        System.out.println("抛出后通知");
    }
    
    public void around(ProceedingJoinPoint point) {
        System.out.println("前置环绕通知");
        try {
            point.proceed();
        }
        catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("后置环绕通知");
    }
}
