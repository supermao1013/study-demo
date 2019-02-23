package com.dalomao.spring.extend.aware;

import org.springframework.beans.factory.BeanNameAware;

/**
 * 【BeanNameAware】
 * Aware 接口是一个标记接口，表示所有实现该接口的类是会被Spring容器选中，并得到某种通知
 * 用法：通过实现该接口可以获取到该bean对应的名称
 */
public class MyBeanNameAware implements BeanNameAware {

    private String beanName;

    @Override
    public void setBeanName(String s) {
        System.out.println("spring扩展测试：MyBeanNameAware BeanNameAware");
        this.beanName = s;
    }
}
