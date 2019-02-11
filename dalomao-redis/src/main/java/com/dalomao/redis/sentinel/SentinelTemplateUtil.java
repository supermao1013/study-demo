package com.dalomao.redis.sentinel;


import java.util.concurrent.LinkedBlockingQueue;  
import java.util.concurrent.ThreadPoolExecutor;  
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;  
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.dao.DataAccessException;  
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisCallback;  
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 使用spring自带的模板
 */
@Component
public class SentinelTemplateUtil {

	// spring 对redis操作的封装，使用了模板模式
	@Resource
	private RedisTemplate<String, Object> redisSentinelTemplate;
 
    private Logger logger = LoggerFactory.getLogger("SentinelTemplateUtil");

    /**
     * set
     * @param key
     * @param value
     */
    public void set(final String key, final String value) {
       redisSentinelTemplate.execute(new RedisCallback<Object>() {
           @Override  
           public Object doInRedis(RedisConnection connection)  
                   throws DataAccessException {  
               connection.set(
                       redisSentinelTemplate.getStringSerializer().serialize(key),
                       redisSentinelTemplate.getStringSerializer().serialize(value));
               logger.debug("save key:" + key + ",value:" + value);  
               return null;  
           }  
       });  
    }
 
    public String get(final String key) {
	   @SuppressWarnings("rawtypes")
	   BoundValueOperations<String, Object> bvo = redisSentinelTemplate.boundValueOps(key);
		return bvo.get().toString();
      /* return redisTemplate.execute(new RedisCallback<String>() {  
           @Override  
           public String doInRedis(RedisConnection connection)  
                   throws DataAccessException {  
               byte[] byteKye = redisTemplate.getStringSerializer().serialize(  
                       key);  
               if (connection.exists(byteKye)) {  
                   byte[] byteValue = connection.get(byteKye);  
                   String value = redisTemplate.getStringSerializer()  
                           .deserialize(byteValue);  
                   logger.info("get key:" + key + ",value:" + value);  
                   return value;  
               }  
               logger.error("valus does not exist!,key:"+key);  
               return null;  
           }  
       });  */
    }
 
    public void delete(final String key) {
       redisSentinelTemplate.execute(new RedisCallback<Object>() {
           public Object doInRedis(RedisConnection connection) {  
               connection.del(redisSentinelTemplate.getStringSerializer().serialize(
                       key));  
               return null;  
           }  
       });  
    }
 
   /** 
    * 线程池并发操作redis 
    *  
    * @param keyvalue 
    */  
 /*  public void mulitThreadSaveAndFind(final String keyvalue) {  
       executor.execute(new Runnable() {  
           @Override  
           public void run() {  
               try {  
                   set(keyvalue, keyvalue);  
                   System.out.println(get(keyvalue));  
               } catch (Throwable th) {  
                   // 防御性容错，避免高并发下的一些问题  
                   logger.error("", th);  
               }  
           }  
       });  
   } */ 
}  