package com.dalomao.redis.queue.queue1;

import java.util.List;

import com.dalomao.redis.util.JedisUtils;

/**
 * 使用List数据类型做消息队列
 */
class ScheduleMQ extends Thread {  

    @Override  
    public void run() {  
        while(true) {  
            //阻塞式brpop，List中无数据时阻塞  
            //参数0表示一直阻塞下去，直到List出现数据  
            List<String> list = JedisUtils.brpop(0, "informList");
            for(String s : list) {  
            	//处理业务逻辑
                System.out.println(s);  
            }  
  
        }  
    }  
}  