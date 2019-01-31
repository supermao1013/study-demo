package com.dalomao.nio.biotimer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 同步阻塞IO模型实现时间服务器-客户端
 */
public class TimeServerClient {

	public static void main(String[] args) {
		int port=8080; //服务端默认端口
		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			//连接服务端
			socket = new Socket("127.0.0.1", port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);

			//传给服务端的指令：获取现在时间
			out.println("QUERY TIME ORDER");
			System.out.println("Send order to server succeed.");

			//等待服务端返回数据
			String resp = in.readLine();
			System.out.println("Now is : "+resp);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(out !=null){
				out.close();
				out = null;
			}
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(socket != null){
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				socket = null;
			}
		}
	}
}
