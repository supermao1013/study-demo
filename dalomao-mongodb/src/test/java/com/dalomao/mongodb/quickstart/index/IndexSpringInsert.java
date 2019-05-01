package com.dalomao.mongodb.quickstart.index;


import com.dalomao.mongodb.quickstart.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Random;
import java.util.UUID;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class IndexSpringInsert {

	private static final Logger logger = LoggerFactory.getLogger(IndexSpringInsert.class);
	
	@Resource
	private MongoOperations tempelate;


	/**
	 * 造数
	 *
	 * 随机生成orderTest数据
	 */
	@Test
	public void batchInsertOrder() {
		String[] userCodes = new String[] { "jack", "james", "five", "six", "jason", "mark", "luffy", "sean", "tony", "star", "denny" };
		for (int i = 0; i <= 500000; i++) {
			Order order = new Order();
			Random rand = new Random();
			int num = rand.nextInt(11);
			order.setUseCode(userCodes[num]);
			order.setOrderCode(UUID.randomUUID().toString());
			order.setOrderTime(RondomDateTest.randomDate("2015-01-01", "2017-10-31"));
			order.setPrice(RondomDateTest.randomBigDecimal(10000, 1));
			tempelate.insert(order);
			System.out.println(i);
		}
	}
  
}
