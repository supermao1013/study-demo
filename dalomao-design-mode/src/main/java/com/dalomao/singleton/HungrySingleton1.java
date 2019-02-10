package com.dalomao.singleton;

/**
 * <p>Package: com.dalomao.demo.singleton</p>
 * <p>Description:饿汉模式1：成员变量实现 </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/9
 **/
public class HungrySingleton1 {
    private final static HungrySingleton1 INSTANCE = new HungrySingleton1();

    private HungrySingleton1(){}

    public static HungrySingleton1 getInstance(){
        return INSTANCE;
    }
}
