package com.dalomao.nio.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIO(同步非阻塞IO)优点：
 * 1、客户端发起的连接操作是异步的，可以通过在多路复用器注册OP_CONNECT等待后续结果，不需要像之前的客户端那样被同步阻塞；
 * 2、SocketChannel的读写操作都是异步的，如果没有可读写的数据它不会同步等待，直接返回，这样IO通信线程就可以处理其他的链路，不需要同步等待这个链路可用；
 * 3、线程模型的优化：由于JDK的Selector在Linux等主流操作系统上通过epoll实现，它没有连接句柄数的限制（只受限于操作系统的最大句柄数或者对单个进程的句柄限制），
 * 		这意味着一个Selector线程可以同时处理成千上万个客户端连接，而且性能不会随着客户端的增加而线性下降。
 * 因此它非常适合做高性能、高负载的网络服务器。
 */
public class BufferDemo {

	public static void main(String[] args) {
		RandomAccessFile file = null;
		try {
			file = new RandomAccessFile("D:\\workspace\\myProject\\study-demo\\dalomao-nio\\src\\main\\resources\\BufferTest.txt","r");

			//定义一个文件通道
			FileChannel fileChannel = file.getChannel();

			//定义一个ByteBuffer，并且初始化大小
			ByteBuffer buf = ByteBuffer.allocate(1024);
			getBufferSign("初始化Buffer",buf);

			//将Channel中的数据写到buffer中
			fileChannel.read(buf);
			getBufferSign("数据读入Buffer",buf);

			//flip将Buffer从写模式切换为读模式
			buf.flip();
			getBufferSign("Buffer切换为读模式",buf);

			//Buffer读数据
			while(buf.hasRemaining()){
				byte b = buf.get();
				System.out.print((char)b);
			}
			System.out.println();
			getBufferSign("Buffer读数据后",buf);

			//将position设置为0，limit保持不变，这时候buffer中的数据仍然不变
			buf.rewind();
			getBufferSign("调用rewind()方法后",buf);
			//继续读取5个byte
			for (int i = 0; i < 5; i++) {
				buf.get();
			}
			getBufferSign("读完5个byte后",buf);

			//将所有未读的数据拷贝到Buffer起始处，然后将position设置到最后一个未读元素的后面，limit属性依然设置为capacity
			buf.compact();
			getBufferSign("compact后",buf);

//			buf.flip();
			buf.position(0);
			buf.limit(20);
			getBufferSign("手动修改position和limit后",buf);
			//Buffer读数据
			while(buf.hasRemaining()){
				byte b = buf.get();
				System.out.print((char)b);
			}
			System.out.println();
			getBufferSign("Buffer读数据后",buf);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (file != null) {
					file.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 打印日志
	 * @param str
	 * @param buf
     */
	public static void getBufferSign(String str, Buffer buf){
		System.out.println(str+": Buffer capacity:"+buf.capacity()+
				"; position:"+buf.position()+
				"; limit:"+buf.limit());
	}

}
