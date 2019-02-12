package com.dalomao.rabbitmq.user;

import com.dalomao.rabbitmq.RmConst;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 */

@Component
public class UserSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        User user=new User();
        user.setName("Mark");
        user.setPass("123456789");
        System.out.println("user send : " + user.getName()+"/"+user.getPass());
        this.rabbitTemplate.convertAndSend(RmConst.QUEUE_USER, user);
    }

}
