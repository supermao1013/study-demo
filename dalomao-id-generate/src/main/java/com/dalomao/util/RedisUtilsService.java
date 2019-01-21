package com.dalomao.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by maohw on 2019/1/21.
 */
@Component
public class RedisUtilsService {

    @Autowired
    private JedisPool jedisPool;

    public Long getIncr(String key, int timeout) {
        Jedis redis = null;

        try {
            redis = jedisPool.getResource();
            /**
             * incr(key)是redis的一个同步方法，原子操作，用于对key自增加1；当key不存在时，则创建值为0的key。
             **/
            long id = redis.incr(key);
            if (id == 1 && timeout > 0) {
                redis.expire(key, timeout); // 设置超时 很重要！很重要！很重要
            }

            return id;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (redis != null) {
                redis.close();
            }
        }

        return null;
    }
}
