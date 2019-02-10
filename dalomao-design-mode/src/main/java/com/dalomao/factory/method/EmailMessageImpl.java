package com.dalomao.factory.method;

/**
 * <p>Package: com.dalomao.demo.factory.method</p>
 * <p>Description:email产品 </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/2
 **/
public class EmailMessageImpl extends AbstractMessage{

    @Override
    public void sendMessage() {
        System.out.println("工厂方法模式：邮件发送，携带参数[" + getMessageParam().get("email") + "]");
    }
}
