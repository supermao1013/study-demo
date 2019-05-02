package com.dalomao.mongodb.quickstart.comment.config;

import com.mongodb.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

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

		/**
		 * 单机架构配置
		 */
	   MongoClient client = new MongoClient(new ServerAddress("192.168.31.41", 27017), mco);

	   /**
		* 密码方式配置：

		MongoCredential createCredential = MongoCredential.createCredential("maohw", "maohw", "123456".toCharArray());
		MongoClient client = new MongoClient(new ServerAddress("192.168.31.41", 27017), Arrays.asList(createCredential), mco);
		*/

		/**
		 * 可复制集架构配置：
		 * MongoDB复制集里Primary节点是不固定的,不固定的,不固定的！所以生产环境千万不要直连Primary,千万不要直连Primary,千万不要直连Primary！
		 * 在options里添加readPreference=secondaryPreferred即可实现读写分离,读请求优先到Secondary节点,从而实现读写分离的功能

		 List<ServerAddress> asList = Arrays.asList(
				new ServerAddress("192.168.1.142", 27018),
				new ServerAddress("192.168.1.142", 27017),
				new ServerAddress("192.168.1.142", 27019));
		MongoClient client = new MongoClient(asList, mco);
		 */

		return client;
	}
}
