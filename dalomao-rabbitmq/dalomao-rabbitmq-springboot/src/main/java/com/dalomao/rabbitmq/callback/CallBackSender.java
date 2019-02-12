package com.dalomao.rabbitmq.callback;

import java.util.UUID;

import com.dalomao.rabbitmq.RmConst;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
/**
 *
 */
@Component
public class CallBackSender  implements  RabbitTemplate.ConfirmCallback{

    @Autowired
    @Qualifier("callback")
    private RabbitTemplate rabbitTemplateNew;

    public void send() {

        rabbitTemplateNew.setConfirmCallback(this);
        String msg="callbackSender : i am callback sender";
        System.out.println(msg );
        CorrelationData correlationData =
                new CorrelationData(UUID.randomUUID().toString());
        System.out.println("callbackSender UUID: " + correlationData.getId());
        this.rabbitTemplateNew.convertAndSend(RmConst.EXCHANGE_TOPIC,
                RmConst.RK_EMAIL, msg, correlationData);
    }

    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("callbakck confirm: " + correlationData.getId());
    }
}
