package com.dalomao.spring.extend.factorypostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * 【BeanFactoryPostProcessor】
 * 在所有bean注册为beanDefinition之后，
 * spring会扫描所有实现了BeanFactoryPostProcessor的bean，并调用其postProcessBeanFactory方法
 * 该扩展主要用来动态修改beanDefinnition的属性等，实用性不高
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory arg0) throws BeansException {
        //通过id获取实例，其实该实例还未实例化，只是被定义成BeanDefinition
        BeanDefinition bd = arg0.getBeanDefinition("beanFactoryPostProcessorVO");

        //每一个BeanDefinition中的所有属性都被封装在MutablePropertyValues的List<PropertyValue>里
        MutablePropertyValues mpv = bd.getPropertyValues();

        //修改属性值
        mpv.addPropertyValue("userName", "zhang san");

        //新增属性值
        mpv.addPropertyValue("school", "xiamen");

        System.out.println("spring扩展测试：MyBeanFactoryPostProcessor postProcessBeanFactory modify beanDefinition");
    }
}
