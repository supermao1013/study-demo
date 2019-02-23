package com.dalomao.spring.extend.aware;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * 【EnvironmentAware】
 * Aware 接口是一个标记接口，表示所有实现该接口的类是会被Spring容器选中，并得到某种通知
 * 用法：普通bean可以通过实现EnvironmentAware接口获得当前应用程序的运行环境对象
 */
public class MyEnvironmentAware implements EnvironmentAware{

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("spring扩展测试：MyEnvironmentAware Environment");
        this.environment = environment;
    }
}
