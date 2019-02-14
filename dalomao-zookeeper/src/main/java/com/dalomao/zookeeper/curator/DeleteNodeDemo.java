package com.dalomao.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * 删除节点
 */
public class DeleteNodeDemo {
	public static void main(String[] args) throws Exception {
		String path = "/zk-client/c1";
		CuratorFramework client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
				.sessionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
		client.start();

		//创建临时节点并赋值，这个临时节点是最后一个节点，父节点是永久的
		client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path, "test".getBytes());

		//读取一个节点的数据内容，同时获取该节点的stat
		Stat stat = new Stat();
		client.getData().storingStatIn(stat).forPath(path);

		//删除路径下的版本号为stat.getVersion()的节点极其所有子节点
		//deletingChildrenIfNeeded()：删除一个节点，并且递归删除其所有的子节点
		client.delete().deletingChildrenIfNeeded().withVersion(stat.getVersion()).forPath(path);
	}
}
