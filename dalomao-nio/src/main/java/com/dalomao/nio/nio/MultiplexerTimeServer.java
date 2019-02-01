package com.dalomao.nio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * 服务端处理线程
 */
public class MultiplexerTimeServer implements Runnable {

	private Selector selector;
	private ServerSocketChannel serverChannel;
	private volatile boolean stop;

	public MultiplexerTimeServer(int port) {
		try {
			//打开ServerSocketChannel
			serverChannel = ServerSocketChannel.open();

			//设置为非阻塞模式
			serverChannel.configureBlocking(false);

			//绑定监听的端口地址
			serverChannel.socket().bind(new InetSocketAddress(port), 1024);

			//创建Selector线程
			selector = Selector.open();

			//将ServerSocketChannel注册到Selector，交给Selector监听
			//与Selector一起使用时，Channel必须处于非阻塞模式下。这意味着不能将FileChannel与Selector一起使用，因为FileChannel不能切换到非阻塞模式。而套接字通道都可以
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);

			System.out.println("The time server is start in port:"+port);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void stop(){
		this.stop = true;
	}

	@Override
	public void run() {
		while(!stop){
			try {
				// select()阻塞到至少有一个通道在你注册的事件上就绪了
				// select()方法返回的int值表示有多少通道已经就绪。亦即，自上次调用select()方法后有多少通道变成就绪状态。
				// 如果调用select()方法，因为有一个通道变成就绪状态，返回了1，若再次调用select()方法，如果另一个通道就绪了，它会再次返回1。
				// 如果对第一个就绪的channel没有做任何操作，现在就有两个就绪的通道，但在每次select()方法调用之间，只有一个通道就绪了
				selector.select();

				// 一旦调用了select()方法，并且返回值表明有一个或更多个通道就绪了，然后可以通过调用selector的selectedKeys()方法，
				// 访问"已选择键集（selected key set）"中的就绪通道。
				Set<SelectionKey> selectionKeys = selector.selectedKeys();

				//开始迭代
				Iterator<SelectionKey> iterator = selectionKeys.iterator();
				SelectionKey selectionKey = null;
				while(iterator.hasNext()){
					selectionKey = iterator.next();
					// 注意：Selector不会自己从已选择键集中移除SelectionKey实例。必须在处理完通道时自己移除。
					// 下次该通道变成就绪时，Selector会再次将其放入已选择键集中
					iterator.remove();
					try {
						handleInput(selectionKey);
					} catch (Exception e) {
						if(selectionKey != null){
							selectionKey.cancel();
							if(selectionKey.channel()!=null){
								selectionKey.channel().close();
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if(selector !=null){
			try {
				//
				selector.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void handleInput(SelectionKey selectionKey) throws IOException {
		//判断key是否有效
		if(selectionKey.isValid()){
			//处理ACCEPT事件
			if (selectionKey.isAcceptable()) {
				ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
				//多路复用器监听到新的客户端连接，处理连接请求，完成TCP三次握手。
				SocketChannel client = server.accept();
				//设置为非阻塞模式
				client.configureBlocking(false);
				// 将新连接注册到多路复用器上，监听其读操作，读取客户端发送的消息
				client.register(selector, SelectionKey.OP_READ);
			}

			//处理READ事件
			if(selectionKey.isReadable()){
				SocketChannel client = (SocketChannel) selectionKey.channel();
				ByteBuffer receivebuffer = ByteBuffer.allocate(1024);
				//读取客户端请求消息到缓冲区
				int count = client.read(receivebuffer);   //非阻塞
				if (count > 0) {
					//读写模式切换
					receivebuffer.flip();
					//remaining()方法返回Buffer中剩余的可读数据长度
					byte[] bytes = new byte[receivebuffer.remaining()];
					//从缓冲区读取消息
					receivebuffer.get(bytes);
					String body = new String(bytes, "UTF-8");
					System.out.println("The time server(Thread:"+Thread.currentThread()+") receive order : "+body);

					//将currentTime响应给客户端（客户端Channel）
					String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
					doWrite(client, currentTime);
				}else if(count < 0){
					selectionKey.channel();
					client.close();
				}else{

				}
			}
		}
	}

	private void doWrite(SocketChannel client, String currentTime) throws IOException {
		if(currentTime != null && currentTime.trim().length()>0){
			ByteBuffer sendbuffer = ByteBuffer.allocate(1024);
			sendbuffer.put(currentTime.getBytes());
			sendbuffer.flip();
			//将客户端响应消息写入到客户端Channel中。
			client.write(sendbuffer);
			System.out.println("服务器端向客户端发送数据--：" + currentTime);
		}else{
			System.out.println("没有数据");
		}
	}

}
