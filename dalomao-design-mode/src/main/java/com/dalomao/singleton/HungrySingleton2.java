package com.dalomao.singleton;

/**
 * <p>Package: com.dalomao.demo.singleton</p>
 * <p>Description:饿汉模式2：静态代码块实现 </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/9
 **/
public class HungrySingleton2 {
    private static HungrySingleton2 instance;

    static {
        instance = new HungrySingleton2();
    }

    private HungrySingleton2() {}

    public static HungrySingleton2 getInstance() {
        return instance;
    }
}
