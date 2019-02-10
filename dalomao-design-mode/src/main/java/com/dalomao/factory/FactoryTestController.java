package com.dalomao.factory;

import com.dalomao.factory.abstractf.AbleFactoryImpl;
import com.dalomao.factory.abstractf.Flyable;
import com.dalomao.factory.abstractf.Moveable;
import com.dalomao.factory.abstractf.Seaable;
import com.dalomao.factory.easy.Noodles;
import com.dalomao.factory.easy.SimpleNoodlesFactory;
import com.dalomao.factory.method.Message;
import com.dalomao.factory.method.MessageFactory;
import com.dalomao.factory.method.MyMessageFactoryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>Package: com.dalomao.demo.factory</p>
 * <p>Description:工厂模式测试 </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/1
 **/
@Controller
@RequestMapping("/factory")
public class FactoryTestController {

    /**
     * 简单工厂模式
     * 特点：
     *  1 它是一个具体的类，非接口 抽象类。有一个重要的create()方法，利用if或者 switch创建产品并返回
     *  2 create()方法通常是静态的，所以也称之为静态工厂
     * 缺点：
     *  1 扩展性差（我想增加一种面条，除了新增一个面条产品类，还需要修改工厂类方法）
     *  2 不同的产品需要不同额外参数的时候不支持
     * @return
     */
    @RequestMapping("/simple.json")
    @ResponseBody
    public String simple() {
        Noodles noodles = SimpleNoodlesFactory.createNoodles(SimpleNoodlesFactory.TYPE_PT);
        noodles.desc();

        return "simple factory success";
    }

    /**
     * 工厂方法模式
     * @return
     */
    @RequestMapping("/method.json")
    @ResponseBody
    public String method() {
        MessageFactory messageFactory = new MyMessageFactoryImpl();

        //邮件通知
        Message message = messageFactory.createMessage("EMAIL");
        message.sendMessage();

        //短信通知
        message = messageFactory.createMessage("SMS");
        message.sendMessage();

        return "method factory success";
    }

    /**
     * 抽象工厂模式
     * @return
     */
    @RequestMapping("/abstract.json")
    @ResponseBody
    public String abstractTest() {
        AbleFactoryImpl factory = new AbleFactoryImpl();
        Flyable flyable = factory.createFlyable();
        flyable.fly(1589);

        Moveable moveable = factory.createMoveable();
        moveable.run(87);

        Seaable seaable = factory.createSeaable();
        seaable.move(60);

        return "abstract factory success";
    }
}
