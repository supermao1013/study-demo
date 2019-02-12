package com.dalomao.producer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 生产者端发送mq消息
 */
@Controller
@RequestMapping("/rabbitmq")
public class RabbitMqController {

    private Logger logger = LoggerFactory.getLogger(RabbitMqController.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送广播信息
     * 消息投递到该交换器底下所有的队列中
     * @param message
     * @return
     */
    @ResponseBody
    @RequestMapping("/fanoutSender")
    public String fanoutSender(@RequestParam("message")String message){
        String opt="";

        try {
            for(int i=0; i<3; i++){
                String str = "Fanout,the message_" + i + " is : " + message;
                logger.info("**************************Send Message:["+str+"]");
                rabbitTemplate.send("fanout-exchange", "", new Message(str.getBytes(), new MessageProperties()));
            }

            opt = "success";
        } catch (Exception e) {
            opt = e.getCause().toString();
        }

        return opt;
    }

    /**
     * 发送主题信息
     * 根据交换器和路由键交消息投递到对应的队列中
     * @param message
     * @return
     */
    @ResponseBody
    @RequestMapping("/topicSender")
    public String topicSender(@RequestParam("message")String message){
        String opt="";

        try {
            String[] severities = {"error","info","warning"};
            String[] modules = {"email","order","user"};
            for(int i=0;i<severities.length;i++){
                for(int j=0;j<modules.length;j++){
                    String routeKey = severities[i]+"." + modules[j];
                    String str = "the message is [rk:"+routeKey+"]["+message+"]";
                    rabbitTemplate.send("topic-exchange", routeKey, new Message(str.getBytes(), new MessageProperties()));
                }
            }

            opt = "success";
        } catch (Exception e) {
            opt = e.getCause().toString();
        }

        return opt;
    }

}
