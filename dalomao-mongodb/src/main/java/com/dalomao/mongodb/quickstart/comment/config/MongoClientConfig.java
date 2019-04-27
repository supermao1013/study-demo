package com.dalomao.mongodb.quickstart.comment.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

	   return client;
	}
}
