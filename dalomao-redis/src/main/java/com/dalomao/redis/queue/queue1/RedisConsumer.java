package com.dalomao.redis.queue.queue1;

/**
 * 消费者
 */
public class RedisConsumer {  
  
    /**  
     * jedis操作List  
     */    
    public static void main(String[] args){  
       ScheduleMQ mq = new ScheduleMQ();
       mq.start();  
    }     
  
} 