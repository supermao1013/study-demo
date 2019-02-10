package com.dalomao.singleton;

/**
 * <p>Package: com.dalomao.demo.singleton</p>
 * <p>Description:懒汉模式-静态内部类 </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/9
 **/
public class IdlerSingleton2 {
    private IdlerSingleton2() {}

    private static class SingletonInstance {
        private static final IdlerSingleton2 INSTANCE = new IdlerSingleton2();
    }

    public static IdlerSingleton2 getInstance() {
        return SingletonInstance.INSTANCE;
    }
}
