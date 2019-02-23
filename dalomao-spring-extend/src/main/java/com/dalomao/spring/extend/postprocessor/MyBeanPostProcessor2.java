package com.dalomao.spring.extend.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 【BeanPostProcessor】
 * 在Spring实例化每个bean之后，会调用所有实现了BeanPostProcessor接口的bean（注意：每个bean实例化都会调用，因此不仅仅是只调用一次）
 * 可以在bean初始化之后做一些其他初始化工作
 */
public class MyBeanPostProcessor2 implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("spring扩展测试：MyBeanPostProcessor2 beforeInit");
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("spring扩展测试：MyBeanPostProcessor2 afterInit");
        return o;
    }
}
