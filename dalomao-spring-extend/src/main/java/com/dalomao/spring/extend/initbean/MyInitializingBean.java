package com.dalomao.spring.extend.initbean;

import org.springframework.beans.factory.InitializingBean;

/**
 * 【InitializingBean】
 * Spring在实例化bean之后，会判断该bean是否实现了InitializingBean并调用afterPropertiesSet进行初始化
 * 可以用来在bean实例化之后做一些初始化工作
 */
public class MyInitializingBean implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("spring扩展测试：MyInitializingBean afterPropertiesSet");
    }
}
