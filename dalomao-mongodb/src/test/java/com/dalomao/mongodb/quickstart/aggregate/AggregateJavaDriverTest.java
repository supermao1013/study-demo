package com.dalomao.mongodb.quickstart.aggregate;


import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.*;
import static com.mongodb.client.model.Aggregates.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.bson.BSON;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.PushOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.operation.OrderBy;

/**
 * 原生java驱动
 *
 * 测试本类请使用测试文件：data/2.测试数据users2-内嵌comments.txt
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AggregateJavaDriverTest {

	private static final Logger logger = LoggerFactory.getLogger(AggregateJavaDriverTest.class);

	private MongoDatabase db;

	private MongoCollection<Document> collection;

	/**
	 * client直接采用注入的方式，因为在MongoClientConfig中已配置
	 */
	@Resource
	private MongoClient client;

	@Before
	public void init(){
		db = client.getDatabase("maohw");
		collection=db.getCollection("users2");
	}


	/**
	 *  面对复杂的查询，如分组、排序等，应该使用聚合查询
	 *
	 *  找出username为lison中的所有评论，然后对这些评论进行分页查询，并按照每条评论的时间做升序排序
	 	db.users2.aggregate([{"$match":{"username":"lison"}},
			 {"$unwind":"$comments"},
			 {$sort:{"comments.commentTime":-1}},
			 {"$project":{"comments":1}},
			 {"$skip":0},
			 {"$limit":3}])
	 */
	@Test
	public void aggregateTest(){
		final List<Document> ret = new ArrayList<Document>();
		Block<Document> printBlock = getBlock(ret);
		List<Bson> aggregates = new ArrayList<Bson>();

		aggregates.add(match(eq("username","lison")));
		aggregates.add(unwind("$comments"));
		aggregates.add(sort(orderBy(ascending("comments.commentTime"))));
		aggregates.add(project(fields(include("comments"))));
		aggregates.add(skip(0));
		aggregates.add(limit(3));

		AggregateIterable<Document> aggregate = collection.aggregate(aggregates);

		printOperation(ret, printBlock, aggregate);
	}

	private void printOperation(List<Document> ret, Block<Document> printBlock,
								AggregateIterable<Document> aggregate) {
		aggregate.forEach(printBlock);
		System.out.println(ret.size());
		ret.removeAll(ret);

	}

	private Block<Document> getBlock(final List<Document> ret) {
		Block<Document> printBlock = new Block<Document>() {
			@Override
			public void apply(Document t) {
				logger.info("---------------------");
				Object object = t.get("comments");
				System.out.println(object);
				logger.info("---------------------");
				ret.add(t);
			}
		};
		return printBlock;
	}
	

}
