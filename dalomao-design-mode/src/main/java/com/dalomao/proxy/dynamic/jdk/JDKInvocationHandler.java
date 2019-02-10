package com.dalomao.proxy.dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * JDK动态代理handler
 **/
public class JDKInvocationHandler implements InvocationHandler {

    private Object object;

    public JDKInvocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk动态代理调用之前的处理");
        method.invoke(object, args);
        System.out.println("jdk动态代理调用之后的处理");
        return null;
    }
}
