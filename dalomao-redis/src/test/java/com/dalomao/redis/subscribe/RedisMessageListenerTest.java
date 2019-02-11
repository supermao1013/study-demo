package com.dalomao.redis.subscribe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * <p>Package: com.dalomao.redis.subscribe</p>
 * <p>Description:TODO </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2019/2/11
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_subscribe.xml")
public class RedisMessageListenerTest {
    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Test
    public void test() {
        String channel = "user:topic";
        redisTemplate.convertAndSend(channel, "hello I am A student, the class time is sunday!!");
    }
}
