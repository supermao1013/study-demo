package com.dalomao.factory.method;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Package: com.dalomao.demo.factory.method</p>
 * <p>Description:工厂实现类，允许多个 </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/2
 **/
public class YourMessageFactoryImpl implements MessageFactory {
    @Override
    public Message createMessage(String messageType) {
        // 这里的方式是：消费者知道自己想要什么产品
        // 若生产何种产品完全由工厂决定，则这里不应该传入控制生产的参数

        Map<String, Object> messageParam = new HashMap<String, Object>();
        Message message = null;

        if ("EMAIL".equals(messageType)) {
            messageParam.put("email", "email is 12345");
            message = new EmailMessageImpl();
        } else if ("SMS".equals(messageType)) {
            messageParam.put("sms", "sms is 110");
            message = new SmsMessageImpl();
        } else {
            messageParam.put("sms", "sms is 110");
            message = new SmsMessageImpl();
        }

        message.setMessageParam(messageParam);

        return message;
    }
}
