package com.dalomao.rabbitmq.topic;

import com.dalomao.rabbitmq.RmConst;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 *
 */
@Component
public class TopicSender {


    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String msg1 = "I am email mesaage msg======";
        System.out.println("email_sender1 : " + msg1);
        this.rabbitTemplate.convertAndSend(RmConst.EXCHANGE_TOPIC, RmConst.RK_EMAIL, msg1);

        String msg2 = "I am user mesaages msg########";
        System.out.println("user_sender2 : " + msg2);
        this.rabbitTemplate.convertAndSend(RmConst.EXCHANGE_TOPIC, RmConst.RK_USER, msg2);
    }

}
