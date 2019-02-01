package com.dalomao.netty.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * 客户端处理
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

	//向服务器发送指令
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		for (int i = 0; i < 5; i++) {
			byte[] req = "QUERY TIME ORDER ".getBytes();
			ByteBuf firstMessage = Unpooled.buffer(req.length);
			firstMessage.writeBytes(req);
			ctx.writeAndFlush(firstMessage);
		}
	}

	//接收服务器的响应
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		try {
			ByteBuf buf = (ByteBuf) msg;
			//buf.readableBytes():获取缓冲区中可读的字节数；
			//根据可读字节数创建数组
			byte[] req = new byte[buf.readableBytes()];
			buf.readBytes(req);
			String body = new String(req, "UTF-8");
			System.out.println("Now is : "+body);
		} finally {
			ReferenceCountUtil.release(msg);
		}
	}

	//异常处理
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		//释放资源
		ctx.close();
	}
	
}
