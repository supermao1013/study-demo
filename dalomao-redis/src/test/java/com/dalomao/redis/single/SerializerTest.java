package com.dalomao.redis.single;


import com.dalomao.redis.single.entity.TCountDetail;
import com.dalomao.redis.util.JedisUtils;
import com.dalomao.redis.util.SerializerUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/*
 * 测试:对象序列化后存redis, 从redis取值后反序列化为JAVA对象
 */
public class SerializerTest {
    @Test
    public void test(){
	    //实体类TCountDetail初始化
	    TCountDetail td = new TCountDetail();
        td.setId("1");
        td.setIp("127.0.0.1");
        td.setOptime(new Date());
        td.setUsername("dalomao");

        //将键值序列化
        byte[] keyBytes = "user:1".getBytes();

        //序列化
        byte[] valueBytes = SerializerUtils.serialize(td);

        //将序列化的数据存入redis
        JedisUtils.setSerializer(keyBytes, valueBytes);

        //从redis获取经序列化后的数据
        byte[] resultBytes = JedisUtils.getSerializer(keyBytes);

        //反序列化,还原成对象
        TCountDetail obj = SerializerUtils.deserialize(resultBytes, TCountDetail.class);

        System.out.println("======="+obj.getUsername());
    }
}
