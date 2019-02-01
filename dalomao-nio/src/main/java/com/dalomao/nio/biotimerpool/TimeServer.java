package com.dalomao.nio.biotimerpool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 同步阻塞IO模型实现时间服务器-服务端
 * 通过线程池实现
 */
public class TimeServer {
	public static void main(String[] args) {
		int port=8080; //服务端默认端口
		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
			System.out.println("The time server is start in port:"+port);
			Socket socket = null;
			TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50, 10000);
			
			while(true){
				socket = server.accept();
//				new Thread(new TimeServerHandler(socket)).start();
				singleExecutor.execute(new TimeServerHandler(socket));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(server!=null){
				System.out.println("The time server is close.");
				try {
					server.close();
					server = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
