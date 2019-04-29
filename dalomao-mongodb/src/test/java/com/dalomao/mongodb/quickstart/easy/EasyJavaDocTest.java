package com.dalomao.mongodb.quickstart.easy;


import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;

/**
 * 原生java驱动 document的操作方式
 *
 * 测试本类请使用测试文件：data/1.测试数据users.txt
 */
public class EasyJavaDocTest {

	private static final Logger logger = LoggerFactory.getLogger(EasyJavaDocTest.class);

	//库
    private MongoDatabase db;

    //集合
    private MongoCollection<Document> doc;

    //客户端
    private MongoClient client;

    @Before
    public void init(){
    	//MongoClient自带连接池，因此通常情况下，一个应用中只需要一个MongoClient实例
    	client = new MongoClient("192.168.31.41",27017);
    	db =client.getDatabase("maohw");//获得库
    	doc = db.getCollection("users");//获得集合
    }

	/**
	 * 插入数据
	 */
	@Test
    public void insertDemo(){
		//文档1
    	Document doc1 = new Document();
    	doc1.append("username", "cang");
    	doc1.append("country", "USA");
    	doc1.append("age", 20);
    	doc1.append("lenght", 1.77f);
    	doc1.append("salary", new BigDecimal("6565.22"));
    	
    	Map<String, String> address1 = new HashMap<String, String>();
    	address1.put("aCode", "0000");
    	address1.put("add", "xxx000");
    	doc1.append("address", address1);
    	
    	Map<String, Object> favorites1 = new HashMap<String, Object>();
    	favorites1.put("movies", Arrays.asList("aa","bb"));
    	favorites1.put("cites", Arrays.asList("东莞","东京"));
    	doc1.append("favorites", favorites1);

    	//文档2
    	Document doc2  = new Document();
    	doc2.append("username", "chen");
    	doc2.append("country", "China");
    	doc2.append("age", 30);
    	doc2.append("lenght", 1.77f);
    	doc2.append("salary", new BigDecimal("8888.22"));
    	Map<String, String> address2 = new HashMap<String, String>();
    	address2.put("aCode", "411000");
    	address2.put("add", "我的地址2");
    	doc1.append("address", address2);
    	Map<String, Object> favorites2 = new HashMap<String, Object>();
    	favorites2.put("movies", Arrays.asList("东游记","一路向东"));
    	favorites2.put("cites", Arrays.asList("珠海","东京"));
    	doc2.append("favorites", favorites2);

    	//新增
    	doc.insertMany(Arrays.asList(doc1,doc2));
    }

    /**
     * 删除
	 */
	@Test
    public void testDelete(){
    	//delete from users where username = ‘cang’
    	DeleteResult deleteMany = doc.deleteMany(Filters.eq("username", "cang"));
    	logger.info(String.valueOf(deleteMany.getDeletedCount()));
    	
    	//delete from users where age >8 and age <35
    	DeleteResult deleteMany2 = doc.deleteMany(Filters.and(Filters.gt("age",8),Filters.lt("age",35)));
    	logger.info(String.valueOf(deleteMany2.getDeletedCount()));
    }

    /**
     * 更新
	 */
	@Test
    public void testUpdate(){
    	//update  users  set age=6 where username = 'cang'
    	UpdateResult updateMany = doc.updateMany(Filters.eq("username", "cang"),
    			                  new Document("$set",new Document("age",6)));//$set表示只修改age这个字段，如果没有$set则表示将该文档全部改成后面的
    	logger.info(String.valueOf(updateMany.getModifiedCount()));
    	
    	//update users  set favorites.movies add "小电影2 ", "小电影3" where favorites.cites  has "东莞"
    	UpdateResult updateMany2 = doc.updateMany(Filters.eq("favorites.cites", "东莞"),
				Updates.addEachToSet("favorites.movies", Arrays.asList( "小电影2 ", "小电影3")));
    	logger.info(String.valueOf(updateMany2.getModifiedCount()));
    }

    /**
     * 查询
	 */
	@Test
    public void testFind(){
		//block用来存放数据
    	final List<Document> ret = new ArrayList<Document>();
    	Block<Document> printBlock = new Block<Document>() {
			@Override
			public void apply(Document t) {
				logger.info(t.toJson());
				ret.add(t);
			}
    		
		};

    	//select * from users  where favorites.cites has "东莞"、"东京"
		FindIterable<Document> find = doc.find(Filters.all("favorites.cites", Arrays.asList("东莞","东京")));
		find.forEach(printBlock);
		logger.info(String.valueOf(ret.size()));
		logger.info(ret.toString());
    	
    	//select * from users  where username like '%c%' and (contry= English or contry = USA)
		ret.removeAll(ret);
		String regexStr = ".*c.*";
		Bson regex = Filters.regex("username", regexStr);
		Bson or = Filters.or(Filters.eq("country","English"),Filters.eq("country","USA"));
		FindIterable<Document> find2 = doc.find(Filters.and(regex,or));
		find2.forEach(printBlock);
		logger.info(String.valueOf(ret.size()));
		logger.info(ret.toString());
    }
    
	
	
	

}
