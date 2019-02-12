package com.dalomao.consumer.fanout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 *
 */
public class H2Service implements MessageListener{
    private Logger logger = LoggerFactory.getLogger(H2Service.class);
    public void onMessage(Message message) {
        logger.info("Get message:"+new String(message.getBody()));
    }
}
