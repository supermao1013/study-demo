package com.dalomao.factory.method;

/**
 * <p>Package: com.dalomao.demo.factory.method</p>
 * <p>Description:工厂接口 </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/2
 **/
public interface MessageFactory {
    Message createMessage(String messageType);
}
