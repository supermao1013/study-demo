package com.dalomao.netty.netty;

import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * 服务端处理
 * 用于对网络时间进行读写操作，通常我们只需要关注channelRead和exceptionCaught方法。
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

	/**
	 * 接收到数据时调用该方法
	 * @param ctx
	 * @param msg
	 * @throws Exception
     */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		try {
			ByteBuf buf = (ByteBuf) msg;
			//buf.readableBytes():获取缓冲区中可读的字节数；
			//根据可读字节数创建数组
			byte[] req = new byte[buf.readableBytes()];
			buf.readBytes(req);
			String body = new String(req, "UTF-8");
			System.out.println("The time server(Thread:" + Thread.currentThread() + ") receive order : " + body);
			String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";

			ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
			//将待发送的消息放到发送缓存数组中
			ctx.writeAndFlush(resp);
		} finally {
			ReferenceCountUtil.release(msg);
		}
	}

	/**
	 * IO错误或其他异常时调用该方法
	 * @param ctx
	 * @param cause
     */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// 记录日志
		cause.printStackTrace();
		ctx.close();
	}
}
