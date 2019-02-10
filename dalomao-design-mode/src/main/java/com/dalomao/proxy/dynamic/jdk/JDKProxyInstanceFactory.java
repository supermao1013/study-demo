package com.dalomao.proxy.dynamic.jdk;

import java.lang.reflect.Proxy;

/**
 * <p>Package: com.dalomao.demo.proxy.dynamic.jdk</p>
 * <p>Description:TODO </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/3
 **/
public class JDKProxyInstanceFactory {
    public static Object proxy(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new JDKInvocationHandler(target)
        );
    }
}
