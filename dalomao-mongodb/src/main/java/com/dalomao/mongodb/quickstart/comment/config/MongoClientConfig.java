package com.dalomao.mongodb.quickstart.comment.config;

import com.mongodb.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class MongoClientConfig {

    /**
     * 创建MongoClient实例
	 * @return
     */
	@Bean
	public MongoClient mongoClient() {


		MongoClientOptions mco = MongoClientOptions.builder()
				.writeConcern(WriteConcern.ACKNOWLEDGED)
				.connectionsPerHost(100)
				.threadsAllowedToBlockForConnectionMultiplier(5)
				.maxWaitTime(120000).connectTimeout(10000).build();

	   MongoClient client = new MongoClient(new ServerAddress("192.168.31.41", 27017), mco);

	   /**
		密码方式配置：
		MongoCredential createCredential = MongoCredential.createCredential("maohw", "maohw", "123456".toCharArray());
		MongoClient client = new MongoClient(new ServerAddress("192.168.31.41", 27017), Arrays.asList(createCredential), mco);
		*/
	   return client;
	}
}
