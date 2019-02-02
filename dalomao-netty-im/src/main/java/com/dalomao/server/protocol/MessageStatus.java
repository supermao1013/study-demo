package com.dalomao.server.protocol;
/**
 * 消息状态，自定义的消息头
 */
public class MessageStatus {

	public static final String LOGIN="LOGIN";
	public static final String LOGOUT="LOGOUT";
	public static final String CHAT="CHAT";
	public static final String SYSTEM="SYSTEM";
	
	public static boolean isSFP(String msg){
		return msg.matches("^\\[(SYSTEM|LOGIN|LOGOUT|CHAT)\\]");
	}
}
