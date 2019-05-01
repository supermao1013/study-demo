package com.dalomao.mongodb.quickstart.update;


import com.dalomao.mongodb.quickstart.entity.Comment;
import com.dalomao.mongodb.quickstart.entity.User2;
import com.mongodb.WriteResult;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Update.PushOperatorBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

/**
 * spring驱动 数组更新
 * 测试本类请使用测试文件：data/2.测试数据users2-内嵌comments.txt
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringUpdateObjArray {

	private static final Logger logger = LoggerFactory.getLogger(SpringUpdateObjArray.class);
	
	@Resource
	private MongoOperations tempelate;

    //--------------------------------------insert demo--------------------------------------------------------------
    /**
     *  给jack老师增加一条评论（$push）
     *
        db.users2.updateOne({"username":"jack"},
                           {"$push":{"comments":{"author":"lison23","content":"ydddyyytttt"}}})
     */
    @Test
    public void addOneComment(){
    	Query query = Query.query(Criteria.where("username").is("jack"));
    	Comment comment = new Comment();
    	comment.setAuthor("lison23");
    	comment.setContent("ydddyyytttt");
		Update push = new Update().push("comments", comment);
		WriteResult updateFirst = tempelate.updateFirst(query, push, User2.class);
		System.out.println(updateFirst.getN());
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
    	Query query = query(Criteria.where("username").is("jack"));
    	Comment comment1 = new Comment();
    	comment1.setAuthor("lison55");
    	comment1.setContent("lison55lison55");
    	Comment comment2 = new Comment();
    	comment2.setAuthor("lison66");
    	comment2.setContent("lison66lison66");
		Update push = new Update().pushAll("comments", new Comment[]{comment1,comment2});
		WriteResult updateFirst = tempelate.updateFirst(query, push, User2.class);
		System.out.println(updateFirst.getN());   	
    }

    /**
     * 给jack老师批量新增两条评论并对数组进行排序（$push,$eachm,$sort）
     * 【注意】会将原有数组的所有元素一起进行排序
     *
         db.users2.updateOne({"username":"jack"},
            "$push": {"comments":{"$each":[ {"author":"lison77","content":"lison55lison55"},
                                            {"author":"lison88","content":"lison66lison66"} ],
                                  $sort: {"author":1} } } })
     */
    @Test
    public void addManySortComment(){
    	Query query = query(Criteria.where("username").is("jack"));
    	Comment comment1 = new Comment();
    	comment1.setAuthor("lison77");
    	comment1.setContent("lison55lison55");
    	Comment comment2 = new Comment();
    	comment2.setAuthor("lison88");
    	comment2.setContent("lison66lison66");

		Update update = new Update();
		PushOperatorBuilder pob = update.push("comments");
		pob.each(comment1,comment2);
		pob.sort(new Sort(new Sort.Order(Direction.DESC, "author")));
		
		System.out.println("---------------");
		WriteResult updateFirst = tempelate.updateFirst(query, update,User2.class);
		System.out.println(updateFirst.getN());   
    }
 
    //--------------------------------------delete demo--------------------------------------------------------------
    /**
     * 删除lison1对jack的所有评论 （批量删除）
     *
     * db.users2.update({"username":“jack"},{"$pull":{"comments":{"author":"lison1"}}})
     */
    @Test
    public void deleteByAuthorComment(){
    	Query query = query(Criteria.where("username").is("jack"));
    	
    	Comment comment1 = new Comment();
    	comment1.setAuthor("lison1");
		Update pull = new Update().pull("comments",comment1);
		WriteResult updateFirst = tempelate.updateFirst(query, pull, User2.class);
		System.out.println(updateFirst.getN());   	
    }
    
    /**
     * 删除lison5对lison评语为“lison是苍老师的小迷弟”的评论（精确删除）
     *
        db.users2.update({"username":"lison"},{"$pull":{"comments":{"author":"lison5","content":"lison是苍老师的小迷弟"}}})
     */
    @Test
    public void deleteByAuthorContentComment(){
    	Query query = query(Criteria.where("username").is("lison"));
    	Comment comment1 = new Comment();
    	comment1.setAuthor("lison5");
    	comment1.setContent("lison是苍老师的小迷弟");
		Update pull = new Update().pull("comments",comment1);
		WriteResult updateFirst = tempelate.updateFirst(query, pull, User2.class);
		System.out.println(updateFirst.getN());  
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
        Query query = query(where("username").is("jack").and("comments.author").is("lison1"));
        Update update = update("comments.$.content","xxoo").set("comments.$.author","lison11");
        WriteResult updateFirst = tempelate.updateFirst(query, update, User2.class);
        System.out.println(updateFirst.getN());
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
		Query query = query(where("_id").is(new ObjectId("5cc8fb7f46176414cced2cb1")));
		Update update = new Update().inc("age", 1);
		FindAndModifyOptions famo = FindAndModifyOptions.options().returnNew(true);
		
		User2 ret = tempelate.findAndModify(query, update,famo, User2.class);
		System.out.println(ret.getAge());
	}
}
