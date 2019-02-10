package com.dalomao.factory.method;

import java.util.Map;

/**
 * <p>Package: com.dalomao.demo.factory.method</p>
 * <p>Description:产品接口 </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/2
 **/
public interface Message {
    Map<String, Object> getMessageParam();

    void setMessageParam(Map<String, Object> messageParam);

    /**
     * 发送通知
     */
    void sendMessage();
}
