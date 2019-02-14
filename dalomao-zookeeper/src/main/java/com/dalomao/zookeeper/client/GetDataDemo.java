package com.dalomao.zookeeper.client;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

/**
 * 创建节点并给节点赋值
 */
public class GetDataDemo {
	public static void main(String[] args) throws InterruptedException {
		String path = "/zk-client";
		ZkClient client = new ZkClient("127.0.0.1:2181", 5000);
		client.createEphemeral(path, "123");//创建节点并赋值

		client.subscribeDataChanges(path, new IZkDataListener() {
			/**
			 * 监听节点的数据变化
             */
			@Override
			public void handleDataChange(String dataPath, Object data) throws Exception {
				System.out.println(dataPath + " changed: " + data);
			}

			/**
			 *
			 * 监听节点的数据删除
             */
			@Override
			public void handleDataDeleted(String dataPath) throws Exception {
				System.out.println(dataPath + " deleted");
			}
		});

		System.out.println(client.readData(path).toString());
		client.writeData(path, "456");
		Thread.sleep(1000);

		client.delete(path);
		Thread.sleep(Integer.MAX_VALUE);
	}
}
