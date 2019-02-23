package com.dalomao.spring.extend.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * 【BeanFactoryAware】
 * Aware 接口是一个标记接口，表示所有实现该接口的类是会被Spring容器选中，并得到某种通知
 * 用法：通过实现BeanFactoryAware可以获得该bean的BeanFactory
 */
public class MyBeanFactoryAware implements BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("spring扩展测试：MyBeanFactoryAware BeanFactory");
        this.beanFactory = beanFactory;
    }
}
