package com.dalomao.mongodb.quickstart.comment;


import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.query.Query.*;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.dalomao.mongodb.quickstart.entity.User2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Update.PushOperatorBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import com.mongodb.WriteResult;

//spring驱动
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CommentSpringDriverTest {

	private static final Logger logger = LoggerFactory.getLogger(CommentSpringDriverTest.class);

	@Resource
	private MongoOperations tempelate;


	/**
	 * 查看人员时加载最新的三条评论，且返回所有字段：
	 * db.users2.find({"username":"lison"},{"comments":{"$slice":[0,3]}}).pretty()
	 */
	@Test
	public void commentFirstTest() {
		//{"username":"lison"}
		Query query = query(where("username").is("lison"));
		//{"comments":{"$slice":[0,3]}
		query.fields().include("comments").slice("comments", 0, 3);
		List<User2> find = tempelate.find(query, User2.class);
		System.out.println("---------------");
		System.out.println(find.size());
		System.out.println("---------------");
	}



	/**
	 * 点击评论的下一页按钮，新加载三条评论，且只返回指定comments字段（_id是必须返回的，可以不管）：
	 * 方法一：db.users2.find({"username":"lison"},{"comments":{"$slice":[3,3]},"$id":1}).pretty();
	 * 方法二：db.users2.find({"username":"lison"},{"comments":{"$slice":[3,3]},"$elemMatch":""}).pretty()
	 */
	@Test
	public void commentNextTest() {
		Query query = query(where("username").is("lison"));
		query.fields().include("comments").slice("comments", 3, 3).include("id");
		List<User2> find = tempelate.find(query, User2.class);
		System.out.println("---------------");
		System.out.println(find.size());
		System.out.println("---------------");
	}

	/**
	 * 测试elemMatch操作符，数组中对象数据要符合查询对象里面所有的字段
	 *
	 * 查找lison5且评语为“lison评论5”的人：
	 * db.users2.find({"comments":{"$elemMatch":{"author" : "lison5","content" : "lison评论5"}}}) .pretty()
	 */
	@Test
	public void testElemMatch() {
		Query query = query(where("comments").elemMatch(where("author").is("lison5").and("content").is("lison评论5")));
		List<User2> find = tempelate.find(query, User2.class);

		System.out.println("数据量：" + find.size());
	}



}
