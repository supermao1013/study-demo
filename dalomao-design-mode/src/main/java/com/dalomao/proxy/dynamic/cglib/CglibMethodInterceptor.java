package com.dalomao.proxy.dynamic.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * <p>Package: com.dalomao.demo.proxy.dynamic.cglib</p>
 * <p>Description:TODO </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/3
 **/
public class CglibMethodInterceptor implements MethodInterceptor {

    private Object target;

    public CglibMethodInterceptor(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        //前置增强
        System.out.println("cglib interfrace before");

        Object res = null;//返回值

        //调用父类的该方法，如果是生成接口的代理时不可调用
        //res = methodProxy.invokeSuper(proxy, args);

        //通过method来调用被代理对象的方法
        if (this.target != null) {
            res = method.invoke(target, args);
        }

        //后置增强
        System.out.println("cglib interfrace after");
        return null;
    }
}
