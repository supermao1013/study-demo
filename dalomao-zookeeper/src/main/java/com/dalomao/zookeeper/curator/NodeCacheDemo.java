package com.dalomao.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class NodeCacheDemo {
	public static void main(String[] args) throws Exception {
		String path = "/zk-client/nodecache";
		CuratorFramework client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
				.sessionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
		client.start();

		client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path, "test".getBytes());

		NodeCache nc = new NodeCache(client, path, false);
		nc.start();
		nc.getListenable().addListener(new NodeCacheListener() {
			@Override
			public void nodeChanged() throws Exception {
				System.out.println("update--current data: " + new String(nc.getCurrentData().getData()));
			}
		});

		//更新一个节点的数据内容
		client.setData().forPath(path, "test123".getBytes());
		Thread.sleep(1000);

		//deletingChildrenIfNeeded()：删除一个节点，并且递归删除其所有的子节点
		client.delete().deletingChildrenIfNeeded().forPath(path);
		Thread.sleep(5000);

		nc.close();
	}
}
