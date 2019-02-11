package com.dalomao.redis.sentinel;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

/**
 * 哨兵模式下，jedis连接池手动创建
 */
public class JedisSentinelPoolUtil {

	/**
	 * jedis哨兵连接池
	 */
	private static JedisSentinelPool pool = null;

	/**
	 * 获取redis哨兵配置参数
	 * @return
     */
	public static Properties getJedisProperties() {
		Properties config = new Properties();
		InputStream is = null;

		try {
			is = JedisSentinelPoolUtil.class.getClassLoader().getResourceAsStream("redis.properties");
			config.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return config;
	}

	/**
	 * 手动创建连接池
	 */

	private static void createJedisPool() {
		// 建立连接池配置参数
		JedisPoolConfig config = new JedisPoolConfig();
		Properties prop = getJedisProperties();

		// 设置最大连接数
		config.setMaxTotal(Integer.valueOf(prop.getProperty("im.hs.server.redis.maxTotal")));
		// 设置最大阻塞时间，记住是毫秒数milliseconds
		config.setMaxWaitMillis(Integer.valueOf(prop.getProperty("im.hs.server.redis.maxWaitTime")));
		// 设置空间连接
		config.setMaxIdle(Integer.valueOf(prop.getProperty("im.hs.server.redis.maxIdle")));
		// jedis实例是否可用
		boolean borrow = prop.getProperty("im.hs.server.redis.testOnBorrow") == "false" ? false : true;
		config.setTestOnBorrow(borrow);
       
		// 创建连接池

		// pool = new JedisPool(config, prop.getProperty("ADDR"),
		// StringUtil.nullToInteger(prop.getProperty("PORT")),
		// StringUtil.nullToInteger(prop.getProperty("TIMEOUT")));//
		// 线程数量限制，IP地址，端口，超时时间

		// 获取redis用戶名和密码
		String password = prop.getProperty("im.hs.server.redis.sentinel.password").toString();
		String masterName = "mymaster";

		//获取哨兵的所有节点
		Set<String> sentinels = new HashSet<String>();
		String hostPort1 = prop.get("im.hs.server.redis.sentinel1.host").toString() + ":" + prop.get("im.hs.server.redis.sentinel1.port").toString();
		String hostPort2 = prop.get("im.hs.server.redis.sentinel2.host").toString() + ":" + prop.get("im.hs.server.redis.sentinel2.port").toString();
		String hostPort3 = prop.get("im.hs.server.redis.sentinel3.host").toString() + ":" + prop.get("im.hs.server.redis.sentinel3.port").toString();
		sentinels.add(hostPort1);
		sentinels.add(hostPort2);
		sentinels.add(hostPort3);

		pool = new JedisSentinelPool(masterName, sentinels, config, password);
	}

	/**
	 * 
	 * 在多线程环境同步初始化
	 * 
	 */
	private static synchronized void poolInit() {
		if (pool == null)
			createJedisPool();
	}

	/**
	 * 获取一个jedis 对象
	 * @return
	 * 
	 */
	public static Jedis getJedis() {
		if (pool == null)
			poolInit();

		return pool.getResource();
	}

	/**
	 * 释放一个连接
	 * @param jedis
	 * 
	 */
	public static void returnRes(Jedis jedis) {
		pool.returnResource(jedis);
	}

	/**
	 * 销毁一个连接
	 * @param jedis
	 * 
	 */
	public static void returnBrokenRes(Jedis jedis) {
		pool.returnBrokenResource(jedis);
	}
}
