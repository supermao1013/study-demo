package com.dalomao.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * 同步创建节点
 */
public class CreateNodeDemo {
	public static void main(String[] args) throws Exception {
		String path = "/zk-client/c1";
		CuratorFramework client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
				.sessionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
		client.start();

		//creatingParentsIfNeeded：递归创建节点
		//withMode(CreateMode.PERSISTENT)：指定创建模式为永久节点
		//forPath(path, "test".getBytes())：创建路径，并赋值为test
		client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path, "test".getBytes());
	}
}
