package com.dalomao.nproducer.producerconfirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *
 * 发送方确认同步模式
 */
public class ProducerConfirm {

    private final static String EXCHANGE_NAME = "producer_confirm";
    private final static String ROUTE_KEY = "error";

    public static void main(String[] args) throws IOException, TimeoutException,
            InterruptedException {
        /**
         * 创建连接连接到MabbitMQ
         */
        ConnectionFactory factory = new ConnectionFactory();
        // 设置MabbitMQ所在主机ip或者主机名
        factory.setHost("127.0.0.1");
        // 创建一个连接
        Connection connection = factory.newConnection();
        // 创建一个信道
        Channel channel = connection.createChannel();
        //将信道设置为发送方确认
        channel.confirmSelect();

        for(int i=0;i<2;i++){
            String msg = "Hello "+(i+1);
            channel.basicPublish(EXCHANGE_NAME,ROUTE_KEY,null,msg.getBytes());
            if (channel.waitForConfirms()){
                System.out.println(ROUTE_KEY+":"+msg);
            }
        }

        // 关闭频道和连接
        channel.close();
        connection.close();
    }

}
