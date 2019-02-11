package com.dalomao.redis.sentinel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * <p>Package: com.dalomao.redis.sentinel</p>
 * <p>Description:TODO </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2019/2/11
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_sentinel.xml")
public class SentinelTemplateUtilTest {

    @Resource
    private SentinelTemplateUtil service;

    @Test
    public void test() throws InterruptedException{
        service.set("name1", "lisi");
        System.out.println("====="	+ service.get("name1"));
    }
}
