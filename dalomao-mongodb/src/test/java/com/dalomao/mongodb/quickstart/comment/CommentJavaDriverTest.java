package com.dalomao.mongodb.quickstart.comment;


import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
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
import static com.mongodb.client.model.Projections.*;

/**
 * 原生java驱动
 *
 * 测试本类请使用测试文件：data/2.测试数据users2-内嵌comments.txt
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CommentJavaDriverTest {

	private static final Logger logger = LoggerFactory.getLogger(CommentJavaDriverTest.class);

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
	 * 查看人员时加载最新的三条评论，且返回所有字段：
	 * db.users2.find({"username":"lison"},{"comments":{"$slice":[0,3]}}).pretty()
	 */
	@Test
	public void commentFirstTest(){
		final List<Document> ret = new ArrayList<Document>();
		Block<Document> printBlock = getBlock(ret);

		FindIterable<Document> find = collection.find(eq("username", "lison"))
				.projection(slice("comments", 0, 3));
		printOperation(ret, printBlock, find);
	}

	/**
	 * 点击评论的下一页按钮，新加载三条评论，且只返回指定comments字段（_id是必须返回的，可以不管）：
	 * 方法一【推荐】：db.users2.find({"username":"lison"},{"comments":{"$slice":[3,3]},"$id":1}).pretty();
	 * 方法二：db.users2.find({"username":"lison"},{"comments":{"$slice":[3,3]},"$elemMatch":""}).pretty()
	 */
	@Test
	public void commentNextTest(){
		final List<Document> ret = new ArrayList<Document>();
		Block<Document> printBlock = getBlock(ret);

		//{"username":"lison"}
		Bson filter = eq("username", "lison");
		//"$slice":[3,3]
		Bson slice = slice("comments", 3, 3);
		//"$id":1
		Bson includeID = include("id");

		//{"comments":{"$slice":[3,3]},"$id":1})
		Bson projection = fields(slice,includeID);

		FindIterable<Document> find = collection.find(filter)
				.projection(projection);
		printOperation(ret, printBlock, find);
	}

	/**
	 * 测试elemMatch操作符，数组中对象数据要符合查询对象里面所有的字段
	 *
	 * 查找lison5且评语为“lison是苍老师的小迷弟”的人：
	 * db.users2.find({"comments":{"$elemMatch":{"author" : "lison5","content" : "lison是苍老师的小迷弟"}}}) .pretty()
	 */
	@Test
	public void testElemMatch(){
		//定义数据的处理类
		final List<Document> ret = new ArrayList<Document>();
		Block<Document> printBlock = getBlock(ret);

		Document filter = new Document().append("author","lison5").append("content","lison评论5");
		Bson elemMatch = Filters.elemMatch("comments",filter );

		FindIterable<Document> find = collection.find(elemMatch);

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
