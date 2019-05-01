package com.dalomao.mongodb.quickstart.update;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.PushOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Arrays;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;

/**
 * java驱动 数组更新
 * 测试本类请使用测试文件：data/2.测试数据users2-内嵌comments.txt
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JavaUpdateObjArray {

	private static final Logger logger = LoggerFactory.getLogger(JavaUpdateObjArray.class);
	
    private MongoDatabase db;


    private MongoCollection<Document> collection;
    
    @Resource
    private MongoClient client;

    @Before
    public void init(){
	    	db = client.getDatabase("maohw");
	    	collection=db.getCollection("users2");
    }
    
    //--------------------------------------insert demo--------------------------------------------------------------

	/**
	 *  给jack老师增加一条评论（$push）
	 *
	 db.users2.updateOne({"username":"jack"},
	 {"$push":{"comments":{"author":"lison23","content":"ydddyyytttt"}}})
	 */
    @Test
    public void addOneComment(){
    	Document comment = new Document().append("author", "lison23")
    			                        .append("content", "ydddyyytttt");
    	Bson filter = eq("username","jack");
		Bson update = push("comments",comment);
		UpdateResult updateOne = collection.updateOne(filter, update);
    	System.out.println(updateOne.getModifiedCount());
    	
    }


	/**
	 *  给jack老师批量新增两条评论（$push,$each）
	 *
	 db.users2.updateOne({"username":"jack"},
		 {"$push":{"comments":
		 {"$each":[{"author":"lison55","content":"lison55lison55"},
		 {"author":"lison66","content":"lison66lison66"}]}}})
	 */
    @Test
    public void addManyComment(){
    	Document comment1 = new Document().append("author", "lison55")
    			                        .append("content", "lison55lison55");
    	Document comment2 = new Document().append("author", "lison66")
                						.append("content", "lison66lison66");
    	
    	Bson filter = eq("username","jack");
		Bson pushEach = pushEach("comments",Arrays.asList(comment1,comment2));
		UpdateResult updateOne = collection.updateOne(filter, pushEach);
    	System.out.println(updateOne.getModifiedCount());
    }


	/**
	 * 给jack老师批量新增两条评论并对数组进行排序（$push,$eachm,$sort）
	 * 【注意】会将原有数组的所有元素一起进行排序
	 *
	 db.users2.updateOne({"username":"jack"},
			 "$push": {"comments":{
	 			"$each":[ {"author":"lison77","content":"lison55lison55"},
			 			{"author":"lison88","content":"lison66lison66"} ],
			 	$sort: {"author":1} } } })
	 */
    @Test
    public void addManySortComment(){
    	Document comment1 = new Document().append("author", "lison77")
    			                        .append("content", "lison55lison55");
    	Document comment2 = new Document().append("author", "lison88")
                						.append("content", "lison66lison66");
    	
    	Bson filter = eq("username","jack");
    	
    	Document sortDoc = new Document().append("author", 1);
    	PushOptions pushOption = new PushOptions().sortDocument(sortDoc);
    	
		Bson pushEach = pushEach("comments",Arrays.asList(comment1,comment2),pushOption);
		
		UpdateResult updateOne = collection.updateOne(filter, pushEach);
    	System.out.println(updateOne.getModifiedCount());
    	
    }
 
    //--------------------------------------delete demo--------------------------------------------------------------
	/**
	 * 删除lison1对jack的所有评论 （批量删除）
	 *
	 * db.users2.update({"username":“jack"},{"$pull":{"comments":{"author":"lison1"}}})
	 */
    @Test
    public void deleteByAuthorComment(){
    	Document comment = new Document().append("author", "lison1");
		Bson filter = eq("username","jack");
		Bson update = pull("comments",comment);
		UpdateResult updateOne = collection.updateOne(filter, update);
		System.out.println(updateOne.getModifiedCount());
    }


	/**
	 * 删除lison5对lison评语为“lison是苍老师的小迷弟”的评论（精确删除）
	 *
	 db.users2.update({"username":"lison"},{"$pull":{"comments":{"author":"lison5","content":"lison是苍老师的小迷弟"}}})
	 */
    @Test
    public void deleteByAuthorContentComment(){
    	Document comment = new Document().append("author", "lison5")
    			                         .append("content", "lison是苍老师的小迷弟");
		Bson filter = eq("username","lison");
		Bson update = pull("comments",comment);
		UpdateResult updateOne = collection.updateOne(filter, update);
		System.out.println(updateOne.getModifiedCount());
    }
    
    //--------------------------------------update demo--------------------------------------------------------------
	/**
	 * 精确修改某人某一条精确的评论，如果有多个符合条件的数据，则修改最后一条数据。无法批量修改数组元素
	 *
	 db.users2.updateMany({"username":"jack","comments.author":"lison1"},
	 {"$set":{"comments.$.content":"xxoo","comments.$.author":"lison10" }})
	 */
  	@Test
  	public void updateOneComment(){
  		Bson filter = and(eq("username","jack"),eq("comments.author","lison1"));
	  	Bson updateContent = set("comments.$.content","xxoo");
	  	Bson updateAuthor = set("comments.$.author","lison10");
	  	Bson update = combine(updateContent,updateAuthor);
	  	UpdateResult updateOne = collection.updateOne(filter, update);
	  	System.out.println(updateOne.getModifiedCount());
	  
  	}
  
  
//--------------------------------------findandModify demo--------------------------------------------------------------
	/**
	 * 使用findandModify方法在修改数据同时返回更新前的数据或更新后的数据
	 * 将年龄+1后，返回修改后的数据
	 *
	 db.users2.findAndModify({
		 "query":{"_id":ObjectId("5cc8fb7f46176414cced2cb1")},
		 "update":{"$inc":{"age":1}},
		 "new":true
	 })
	 */
	  @Test
	  public void findAndModifyTest(){
		  Bson filter = eq("_id",new ObjectId("5cc8fb7f46176414cced2cb1"));
		  Bson update = inc("age",1);
	//	  //实例化findAndModify的配置选项
		  FindOneAndUpdateOptions fauo = new FindOneAndUpdateOptions();
	//	  //配置"new":true
		  fauo.returnDocument(ReturnDocument.AFTER);//
		  MongoCollection<Document> numCollection = db.getCollection("users2");
		  Document ret = numCollection.findOneAndUpdate(filter, update,fauo);
		  System.out.println(ret.toJson());
	  }
}
