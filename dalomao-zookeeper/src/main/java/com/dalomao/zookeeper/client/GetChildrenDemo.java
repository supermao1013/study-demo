package com.dalomao.zookeeper.client;

import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

/**
 * 创建并监听节点变化
 */
public class GetChildrenDemo {
	public static void main(String[] args) throws InterruptedException {
		String path = "/zk-client";
		ZkClient client = new ZkClient("127.0.0.1:2181", 5000);
		client.subscribeChildChanges(path, new IZkChildListener() {
			@Override
			public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
				System.out.println(parentPath + "的子节点发生变化: " + currentChilds);
			}
		});

		client.createPersistent(path);
		Thread.sleep(1000);

		System.out.println(client.getChildren(path));

		client.createPersistent(path + "/c1");
		Thread.sleep(1000);

		client.delete(path + "/c1");
		Thread.sleep(1000);

		client.delete(path);
		Thread.sleep(Integer.MAX_VALUE);
	}
}
