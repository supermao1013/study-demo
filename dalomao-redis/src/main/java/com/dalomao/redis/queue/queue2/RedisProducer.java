package com.dalomao.redis.queue.queue2;


import com.dalomao.redis.util.JedisUtils;

import java.util.Random;
import java.util.UUID;

/*
 * 生产者
 */

public class RedisProducer implements Runnable{  

    public void run() {  
        Random random = new Random();  
        while(true){  
            try{  
                Thread.sleep(random.nextInt(600) + 600);  
                // 模拟生成一个任务  
                UUID orderId = UUID.randomUUID();  
                //将任务插入任务队列：task-queue  
                JedisUtils.lpush("test-queue", orderId.toString());
                System.out.println("队列最左端进入了新的消息数据： " + orderId);  
  
            }catch(Exception e){  
                e.printStackTrace();  
            }  
        }  
          
    }  
  
}  