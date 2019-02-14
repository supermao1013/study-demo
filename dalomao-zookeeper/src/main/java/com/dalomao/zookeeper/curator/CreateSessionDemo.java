package com.dalomao.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CreateSessionDemo {
	public static void main(String[] args) throws InterruptedException {
		//重试策略
		RetryPolicy policy = new ExponentialBackoffRetry(1000, 3);

		//sessionTimeoutMs：会话超时时间，单位毫秒，默认60000ms
		//connectionTimeoutMs：连接创建超时时间，单位毫秒，默认60000ms
		//connectionString：服务器列表，格式host1:port1,host2:port2
		CuratorFramework client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
				.sessionTimeoutMs(5000).retryPolicy(policy).build();

		//启动客户端
		client.start();

		Thread.sleep(Integer.MAX_VALUE);
	}
}
