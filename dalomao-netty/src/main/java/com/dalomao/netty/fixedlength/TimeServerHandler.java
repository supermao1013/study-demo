package com.dalomao.netty.fixedlength;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 服务端处理
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

	private int counter;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String body = (String) msg;
		System.out.println("The time server(Thread:"+Thread.currentThread()+") receive order : "+body+". the counter is : "+ ++counter);
		String currentTime = body;

		ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());

		//将待发送的消息放到发送缓存数组中
		ctx.writeAndFlush(resp);
	}
}
