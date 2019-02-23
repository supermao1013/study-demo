package com.dalomao.spring.extend;

import com.dalomao.spring.extend.factorypostprocessor.BeanFactoryPostProcessorVO;
import com.dalomao.spring.extend.listenersync.MyEvent;
import com.dalomao.spring.extend.listenerasync.AsyncEvent;
import com.dalomao.spring.extend.listenerasync.SpringContextUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Spring扩展结果查看
 */
@Controller
@RequestMapping("/springExtend")
public class SpringExtendResultController implements ApplicationContextAware{

    private ApplicationContext applicationContext;

    @Autowired
    private BeanFactoryPostProcessorVO beanFactoryPostProcessorVO;

    /**
     * 首页
     * @return String
     */
    @RequestMapping("/index.htm")
    public String index() {
        return "/springExtend/index";
    }

    @RequestMapping("/beanFactoryPostProcessor.json")
    @ResponseBody
    public String beanFactoryPostProcessor() {
        String s = "通过MyBeanFactoryPostProcessor修改后的BeanFactoryPostProcessorVO属性值为：" +
                "userName=" + beanFactoryPostProcessorVO.getUserName() + "," +
                "age=" + beanFactoryPostProcessorVO.getAge() + "," +
                "school=" + beanFactoryPostProcessorVO.getSchool();
        System.out.println(s);
        return s;
    }

    /**
     * 同步监听器测试
     * @param request 请求体
     * @return String
     */
    @RequestMapping("/applicationListenerForSync.json")
    @ResponseBody
    public String applicationListener(HttpServletRequest request) {
        MyEvent event = new MyEvent("实体数据", "参数1", "参数2");
        //使用ApplicationContext发布事件，给那些监听者发布消息
        //观察者模式
        // subject --> MyEvent
        // obverse --> MyListen、MyListen2
        //同步通知，按顺序通知每一个监听者，并等待执行完毕
        applicationContext.publishEvent(event);

        return "测试成功";
    }

    /**
     * 异步监听器测试
     * @param request
     * @return
     */
    @RequestMapping("/applicationListenerForAsync.json")
    @ResponseBody
    public String applicationListener2(HttpServletRequest request) {
        AsyncEvent event = new AsyncEvent("实体数据", "参数1");
        //使用ApplicationContext发布事件，给那些监听者发布消息
        //观察者模式
        // subject --> MyEvent
        // obverse --> MyListen、MyListen2
        //异步通知
        SpringContextUtil.publishEvent(event);
        System.out.println("发送成功");
        return "测试成功";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
