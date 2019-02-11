package com.dalomao.redis.queue.queue1;

import com.dalomao.redis.util.JedisUtils;

/**
 * 生产者
 */
public class RedisProducer {
  
    /**  
     * jedis操作List  
     */    
    public static void main(String[] args){  
        for(int i = 0; i<10; i++) {
            JedisUtils.lpush("informList","orderIdadb_" + i);
        }  
    }  
  
} 