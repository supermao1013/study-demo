package com.dalomao.proxy;

import com.dalomao.proxy.dynamic.cglib.CglibMethodInterceptor;
import com.dalomao.proxy.dynamic.jdk.IPepole;
import com.dalomao.proxy.dynamic.jdk.JDKProxyInstanceFactory;
import com.dalomao.proxy.dynamic.jdk.StudentService;
import com.dalomao.proxy.dynamic.jdk.TeacherService;
import com.dalomao.proxy.statical.BrokerProxy;
import com.dalomao.proxy.statical.Girl;
import com.dalomao.proxy.statical.TeacherCangImpl;
import com.dalomao.proxy.statical.TuHao;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <p>Package: com.dalomao.demo.proxy</p>
 * <p>Description:TODO </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/2
 **/
@Controller
@RequestMapping("/proxy")
public class ProxyTestController {
    /**
     * 静态代理
     * @return
     */
    @RequestMapping("/statical.json")
    @ResponseBody
    public String statical() {
        TuHao th = new TuHao(1.7F);
        Girl tc = new TeacherCangImpl();

        //土豪通过经纪人约会苍老师
        BrokerProxy proxy = new BrokerProxy();
        proxy.setGirl(tc);
        th.dating(proxy);

        return "proxy statical success";
    }

    /**
     * JDK动态代理测试
     * JDK动态代理只能为接口创建代理实例
     * @return
     */
    @RequestMapping("/jdkDynamicProxy.json")
    @ResponseBody
    public String jdkDynamicProxy() {
        IPepole teacher = (IPepole) JDKProxyInstanceFactory.proxy(new TeacherService());
        teacher.eat();
        teacher.career();

        IPepole student = (IPepole) JDKProxyInstanceFactory.proxy(new StudentService());
        student.eat();
        student.career();

        return "jdk dynamic proxy success";
    }

    /**
     * 生成jdk动态代理类的class文件
     */
    @RequestMapping("/jdkDynamicProxyClassFile.json")
    @ResponseBody
    public String createProxyClassFile() {
        byte[] data = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{IPepole.class});
        try {
            FileOutputStream out = new FileOutputStream("D:\\$Proxy0.class");
            out.write(data);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "生成成功，路径为D:\\$Proxy0.class";
    }

    /**
     * cglib动态代理测试
     * cglib可以为接口，也可以为实现类创建代理实例
     * @return
     */
    @RequestMapping("/cglibDynamicProxy.json")
    @ResponseBody
    public String cglibDynamicProxy() {
        /**
         * 为接口创建代理
         */
        Enhancer enhancer1 = new Enhancer();
        enhancer1.setCallback(new CglibMethodInterceptor(new StudentService()));//设置增强回调
        enhancer1.setInterfaces(new Class[]{IPepole.class});//对接口生成代理对象

        IPepole proxy1 = (IPepole) enhancer1.create();
        proxy1.eat();
        proxy1.career();

        /**
         * 为实体类创建代理
         */
        Enhancer enhancer2 = new Enhancer();
        enhancer2.setCallback(new CglibMethodInterceptor(new TeacherService()));
        enhancer2.setSuperclass(TeacherService.class);//对类生成代理对象
        enhancer2.setInterfaces(null);

        //当有多个callback时，需要通过callbackFilter来指定被代理方法使用第几个callback
        /* enhancer2.setCallbackFilter(new CallbackFilter() {
            @Override
            public int accept(Method method) {
                return 0;
            }
        }); */

        TeacherService proxy2 = (TeacherService) enhancer2.create();
        proxy2.eat();
        proxy2.career();

        return "cglib dynamic proxy success";
    }
}
