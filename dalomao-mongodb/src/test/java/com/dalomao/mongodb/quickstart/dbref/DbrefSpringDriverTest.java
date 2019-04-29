package com.dalomao.mongodb.quickstart.dbref;


import com.dalomao.mongodb.quickstart.entity.User3;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * spring驱动
 *
 * 测试本类请使用测试文件：data/3.测试数据user3-DBRef查询comments.txt
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DbrefSpringDriverTest {
	private static final Logger logger = LoggerFactory.getLogger(DbrefSpringDriverTest.class);

	@Resource
	private MongoOperations tempelate;

	@Test
	//(1)注意相关的实体bean要加上注解@document，@dbRef
	//(2)spring对dbRef进行了封装，发起了两次查询请求
	public void dbRefTest(){
		List<User3> users = tempelate.findAll(User3.class);
		System.out.println("----------------------------");
		System.out.println(users);
		System.out.println("----------------------------");
	}

}
