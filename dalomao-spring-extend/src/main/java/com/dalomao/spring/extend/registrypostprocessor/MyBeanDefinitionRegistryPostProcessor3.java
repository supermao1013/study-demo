package com.dalomao.spring.extend.registrypostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.Ordered;

/**
 * 【BeanDefinitionRegistryPostProcessor】
 * 在所有bean注册为beanDefinition之后，
 * spring会扫描所有实现了BeanDefinitionRegistryPostProcessor的bean，并调用其postProcessBeanDefinitionRegistry方法，
 * 调用优先级是根据是否又实现另外一个优先级类并重写order值，值越小优先级越高
 * 该扩展主要用来动态修改beanDefinnition的属性等，实用性不高
 * 和BeanFactoryPostProcessor功能差不多
 */
public class MyBeanDefinitionRegistryPostProcessor3 implements BeanDefinitionRegistryPostProcessor,Ordered {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        System.out.println("spring扩展测试：MyBeanDefinitionRegistryPostProcessor3 Ordered 0");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
