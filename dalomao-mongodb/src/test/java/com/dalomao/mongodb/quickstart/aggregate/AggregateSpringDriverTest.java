package com.dalomao.mongodb.quickstart.aggregate;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * spring驱动
 *
 * 测试本类请使用测试文件：data/2.测试数据users2-内嵌comments.txt
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AggregateSpringDriverTest {
	private static final Logger logger = LoggerFactory.getLogger(AggregateSpringDriverTest.class);

	@Resource
	private MongoOperations tempelate;

	/**
	 *  面对复杂的查询，如分组、排序等，应该使用聚合查询
	 *
	 *  找出username为lison中的所有评论，然后对这些评论进行分页查询，并按照每条评论的时间做升序排序
		 db.users.aggregate([{"$match":{"username":"lison"}},
			 {"$unwind":"$comments"},
			 {$sort:{"comments.commentTime":-1}},
			 {"$project":{"comments":1}},
			 {"$skip":0},
			 {"$limit":3}])

	 */
	@Test
	public void aggregateTest() {
		Aggregation aggs = newAggregation(
				match(where("username").is("lison")),
				unwind("comments"),
				sort(Direction.ASC, "comments.commentTime"),
				project("comments"),
				skip(0),
				limit(3));
		System.out.println("---------------");
		AggregationResults<Object> aggregate = tempelate.aggregate(aggs, "users2",	Object.class);
		System.out.println("---------------");
		List<Object> mappedResults = aggregate.getMappedResults();
		System.out.println(mappedResults.size());

	}
}
