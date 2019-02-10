package com.dalomao.singleton;

/**
 * <p>Package: com.dalomao.demo.singleton</p>
 * <p>Description:懒汉模式-双重检查 </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/9
 **/
public class IdlerSingleton1 {
    //保证线程可见性
    private static volatile IdlerSingleton1 singleton;

    private IdlerSingleton1() {}

    //双重检查
    public static IdlerSingleton1 getInstance() {
        if (singleton == null) {
            synchronized (IdlerSingleton1.class) {
                if (singleton == null) {
                    singleton = new IdlerSingleton1();
                }
            }
        }
        return singleton;
    }
}
