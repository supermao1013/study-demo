package com.dalomao.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by maohw on 2019/2/12.
 */
@SpringBootApplication
public class SpringbootRmApplication {
    @Bean
    public Queue helloQueue() {
        return new Queue(RmConst.QUEUE_HELLO);
    }

    @Bean
    public Queue userQueue() {
        return new Queue(RmConst.QUEUE_USER);
    }

    //===============以下是验证topic Exchange的队列==========
    @Bean
    public Queue queueEmailMessage() {
        return new Queue(RmConst.QUEUE_TOPIC_EMAIL);
    }

    @Bean
    public Queue queueUserMessages() {
        return new Queue(RmConst.QUEUE_TOPIC_USER);
    }
    //===============以上是验证topic Exchange的队列==========


    @Bean
    TopicExchange exchange() {
        return new TopicExchange(RmConst.EXCHANGE_TOPIC);
    }

    /**
     * 将队列topic.message与exchange绑定，binding_key为topic.message,就是完全匹配
     * @return
     */
    @Bean
    Binding bindingEmailExchangeMessage() {
        return BindingBuilder
                .bind(queueEmailMessage())
                .to(exchange())
                .with("sb.*.email");
    }

    /**
     * 将队列topic.messages与exchange绑定，binding_key为topic.#,模糊匹配
     * @return
     */
    @Bean
    Binding bindingUserExchangeMessages() {
        return BindingBuilder
                .bind(queueUserMessages())
                .to(exchange())
                .with("sb.*.user");
    }

	/*
	//===============以下是验证Fanout Exchange的队列==========
	@Bean
	public Queue AMessage() {
		return new Queue(FANOUT_QUEUE+".A");
	}

	@Bean
	public Queue BMessage() {
		return new Queue(FANOUT_QUEUE+".B");
	}

	@Bean
	public Queue CMessage() {
		return new Queue(FANOUT_QUEUE+".C");
	}
	//===============以上是验证Fanout Exchange的队列==========

	FanoutExchange fanoutExchange() {
		return new FanoutExchange(FANOUT_EXCHANGE);
	}

	@Bean
	Binding bindingExchangeA(Queue AMessage,FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(AMessage).to(fanoutExchange);
	}

	@Bean
	Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(BMessage).to(fanoutExchange);
	}

	@Bean
	Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(CMessage).to(fanoutExchange);
	}
	*/

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRmApplication.class, args);
    }
}
