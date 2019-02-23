package com.dalomao.spring.extend.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 【ApplicationcontextAware】
 * Aware 接口是一个标记接口，表示所有实现该接口的类是会被Spring容器选中，并得到某种通知
 * 调用时刻：bean实例化之后，ApplicationContextAwareProcessor.postProcessBeforeInitialization()，
 * 对继承自ApplicationContextAware的bean进行处理，调用其setApplicationContext
 * 用法：普通bean可以通过实现ApplicationcontextAware接口获得应用上下文
 */
public class MyApplicationcontextAware implements ApplicationContextAware{

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("spring扩展测试：MyApplicationcontextAware ApplicationContext");
        this.applicationContext = applicationContext;
    }
}
