package com.dalomao.mongodb.quickstart.easy;


import com.dalomao.mongodb.quickstart.entity.Address;
import com.dalomao.mongodb.quickstart.entity.Favorites;
import com.dalomao.mongodb.quickstart.entity.User;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//原生java驱动 Pojo的操作方式
public class EasyJavaPojoTest {

	private static final Logger logger = LoggerFactory.getLogger(EasyJavaPojoTest.class);
	
    private MongoDatabase db;
    
    private MongoCollection<User> doc;
    
    private MongoClient client;

    @Before
    public void init(){
    	//编解码器的list
    	List<CodecRegistry> codecResgistes = new ArrayList<CodecRegistry>();
    	//编解码器的list加入默认的编解码器结合，原生mongodb提供了默认的编解码器
    	codecResgistes.add(MongoClient.getDefaultCodecRegistry());
    	//生成一个pojo的编解码器，mongodb同样有提供，但是并没有加入默认的编解码器
    	CodecRegistry pojoProviders = CodecRegistries.
    			fromProviders(PojoCodecProvider.builder().automatic(true).build());
    	codecResgistes.add(pojoProviders);

    	//通过编解码器的list生成编解码器注册中心
    	CodecRegistry registry = CodecRegistries.fromRegistries(codecResgistes);
    	
    	//把编解码器注册中心放入MongoClientOptions
    	MongoClientOptions build = MongoClientOptions.builder().
    			codecRegistry(registry).build();
    	ServerAddress serverAddress = new ServerAddress("192.168.31.41",27017);
		client = new MongoClient(serverAddress, build);

    	db = client.getDatabase("maohw");//获取库

    	doc = db.getCollection("users",User.class);//获取集合，并和entity映射起来
    }

    /**
     * 新增文档
	 */
	@Test
    public void insertDemo(){
    	//文档1
    	User user = new User();
    	user.setUsername("cang");
    	user.setCountry("USA");
    	user.setAge(20);
    	user.setLenght(1.77f);
    	user.setSalary(new BigDecimal("6265.22"));
    	Address address1 = new Address();
    	address1.setaCode("411222");
    	address1.setAdd("sdfsdf");
    	user.setAddress(address1);
    	Favorites favorites1 = new Favorites();
    	favorites1.setCites(Arrays.asList("东莞","东京"));
    	favorites1.setMovies(Arrays.asList("西游记","一路向西"));
    	user.setFavorites(favorites1);

    	//文档2
    	User user1 = new User();
    	user1.setUsername("chen");
    	user1.setCountry("China");
    	user1.setAge(30);
    	user1.setLenght(1.77f);
    	user1.setSalary(new BigDecimal("6885.22"));
    	Address address2 = new Address();
    	address2.setaCode("411000");
    	address2.setAdd("我的地址2");
    	user1.setAddress(address2);
    	Favorites favorites2 = new Favorites();
    	favorites2.setCites(Arrays.asList("珠海","东京"));
    	favorites2.setMovies(Arrays.asList("东游记","一路向东"));
    	user1.setFavorites(favorites2);
    	
    	doc.insertMany(Arrays.asList(user,user1));
    }

    /**
     * 删除文档，pojo编程方式不影响删除
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
     * 更新，pojo编程方式不影响更新
	 */
	@Test
    public void testUpdate(){
    	//update  users  set age=6 where username = 'cang'
    	UpdateResult updateMany = doc.updateMany(Filters.eq("username", "cang"),
    			                  new Document("$set",new Document("age",6)));
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
    	final List<User> ret = new ArrayList<User>();
    	Block<User> printBlock = new Block<User>() {
			@Override
			public void apply(User t) {
				System.out.println(t.getUsername());
				System.out.println(t.getSalary());
				ret.add(t);
			}
    		
		};
		
    	//select * from users  where favorites.cites has "东莞"、"东京"
		FindIterable<User> find = doc.find(Filters.all("favorites.cites", Arrays.asList("东莞","东京")));
		find.forEach(printBlock);
		logger.info(String.valueOf(ret.size()));

    	//select * from users  where username like '%c%' and (contry= English or contry = USA)
		ret.removeAll(ret);
		String regexStr = ".*c.*";
		Bson regex = Filters.regex("username", regexStr);
		Bson or = Filters.or(Filters.eq("country","English"),Filters.eq("country","USA"));
		FindIterable<User> find2 = doc.find(Filters.and(regex,or));
		find2.forEach(printBlock);

		logger.info(String.valueOf(ret.size()));
    }
    
	
	
	

}
