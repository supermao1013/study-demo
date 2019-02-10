package com.dalomao.proxy.statical;

/**
 * <p>Package: com.dalomao.demo.proxy.statical</p>
 * <p>Description:苍老师的经纪人（代理） </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/2
 **/
public class BrokerProxy implements Girl{
    /**
     * 经纪人必须持有女孩子
     */
    private Girl girl;
    public Girl getGirl() {
        return girl;
    }
    public void setGirl(Girl girl) {
        this.girl = girl;
    }

    @Override
    public boolean dating(float length) {
        System.out.println("静态代理模式：前置增强，老板，这个我试过了，很不错，推荐给你！");

        boolean flag = this.getGirl().dating(length);

        System.out.println("静态代理模式：后置增强，老板，你觉得怎样，欢迎下次再约！");

        return flag;
    }
}
