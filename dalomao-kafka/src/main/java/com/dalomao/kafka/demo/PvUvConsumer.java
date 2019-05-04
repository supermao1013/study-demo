package com.dalomao.kafka.demo;


import com.dalomao.kafka.util.KafkaCommons;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Kafka消费者，持续不断地消费数据
 *
 *
 */
public class PvUvConsumer {
    private static final String TOPIC_ID = "rt_dn_pvuv";

    public static void main(String[] args) {
        while (true) {
            List<String> msgs = KafkaCommons.getMsgFromKafka(TOPIC_ID);

            for (String msg : msgs) {
                System.out.println("Kafka Consumer Msg: " + msg);
            }

            System.out.println("##########Time【"
                    + new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date())
                    + "】##########");

            try {
                Thread.sleep(6000);
            } catch (Exception e) {
                System.out.println("Error Happens: " + e.getMessage());
            }
        }
    }
}
