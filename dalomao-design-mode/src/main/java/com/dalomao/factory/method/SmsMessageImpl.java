package com.dalomao.factory.method;

/**
 * <p>Package: com.dalomao.demo.factory.method</p>
 * <p>Description:短信产品 </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/2
 **/
public class SmsMessageImpl extends AbstractMessage{

    @Override
    public void sendMessage() {
        System.out.println("工厂方法模式：短信发送，携带参数[" + getMessageParam().get("sms") + "]");
    }
}
