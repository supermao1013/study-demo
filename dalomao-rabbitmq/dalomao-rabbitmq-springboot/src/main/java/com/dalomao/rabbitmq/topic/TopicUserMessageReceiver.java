package com.dalomao.rabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
@RabbitListener(queues = "sb.info.user")
public class TopicUserMessageReceiver {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("TopicUserMessageReceiver  : " +msg);
    }

}