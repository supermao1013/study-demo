package com.dalomao.mongodb.quickstart.dbref;


import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * 原生java驱动
 *
 * 测试本类请使用测试文件：data/3.测试数据user3-DBRef查询comments.txt
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DbrefJavaDriverTest {

	private static final Logger logger = LoggerFactory.getLogger(DbrefJavaDriverTest.class);

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
		collection=db.getCollection("users3");
	}

	/**
	 * dbRef测试
	 * dbref其实就是关联关系的信息载体，查询本身时并不会去关联数据
	 */
	@Test
	public void dbRefTest(){
		final List<Document> ret = new ArrayList<Document>();
		Block<Document> printBlock = getBlock(ret);
		FindIterable<Document> find = collection.find(eq("username", "lison"));
		printOperation(ret, printBlock, find);
	}

	private void printOperation(final List<Document> ret,
								Block<Document> printBlock, FindIterable<Document> find) {
		find.forEach(printBlock);
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
