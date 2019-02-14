package com.dalomao.zookeeper.client;

import org.I0Itec.zkclient.ZkClient;

/**
 * 创建zk节点
 */
public class CreateNodeDemo {
	public static void main(String[] args) {
		ZkClient client = new ZkClient("127.0.0.1:2181", 5000);
		String path = "/zk-client/c1";
		// 递归创建节点
		client.createPersistent(path, true);
	}
}
