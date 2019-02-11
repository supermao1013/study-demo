package com.dalomao.redis.sentinel;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

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
public class JedisSentinelPoolUtilTest {

    @Test
    public void test() throws InterruptedException {
        Jedis jedis = null;
        try {
            jedis = JedisSentinelPoolUtil.getJedis();
            jedis.set("name", "zhangsan");
            System.out.println(jedis.get("name"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JedisSentinelPoolUtil.returnRes(jedis);
        }
    }

    @Test
    public void testBasic() throws InterruptedException {
        Set<String> sentinels = new HashSet<String>();
        String hostAndPort1 = "192.168.1.111:26379";
        String hostAndPort2 = "192.168.1.111:26380";
        String hostAndPort3 = "192.168.1.111:26381";
        sentinels.add(hostAndPort1);
        sentinels.add(hostAndPort2);
        sentinels.add(hostAndPort3);
        String clusterName = "mymaster";
        String password = "12345678";

        JedisSentinelPool redisSentinelJedisPool = new JedisSentinelPool(clusterName, sentinels, password);
        Jedis jedis = null;
        try {
            jedis = redisSentinelJedisPool.getResource();
            jedis.set("name2", "haha");
            System.out.println(jedis.get("name2"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redisSentinelJedisPool.returnBrokenResource(jedis);
        }

        redisSentinelJedisPool.close();
    }
}
