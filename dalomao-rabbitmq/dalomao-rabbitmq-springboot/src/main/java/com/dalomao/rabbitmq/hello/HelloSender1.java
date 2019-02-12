package com.dalomao.rabbitmq.hello;

import com.dalomao.rabbitmq.RmConst;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class HelloSender1 {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String msg) {
        String sendMsg = msg +"---"+ System.currentTimeMillis();;
        System.out.println("Sender : " + sendMsg);
        this.rabbitTemplate.convertAndSend(RmConst.QUEUE_HELLO, sendMsg);
    }

}
