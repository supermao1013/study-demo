package com.dalomao.spring.extend.aop.xmlaop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationContext.xml")
public class MyTest implements ApplicationContextAware {
    
    public ApplicationContext context;

    @Test
    public void test1() {
        MyService service = (MyService)context.getBean("myservice");
        service.execute();
    }

    /**
     * 给MyService增强方法
     */
    @Test
    public void test2() {
        IntroductionIntf service = (IntroductionIntf)context.getBean("myservice");
        service.extend();
    }
    
    @Override
    public void setApplicationContext(ApplicationContext arg0)
            throws BeansException {
        this.context = arg0;
        
    }
    
}
