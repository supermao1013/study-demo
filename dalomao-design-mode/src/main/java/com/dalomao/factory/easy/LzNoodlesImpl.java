package com.dalomao.factory.easy;

/**
 * <p>Package: com.dalomao.demo.factory.easy</p>
 * <p>Description:兰州拉面（具体实现类） </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/1
 **/
public class LzNoodlesImpl implements Noodles {
    @Override
    public void desc() {
        System.out.println("简单工厂模式：兰州拉面，闻名遐迩");
    }
}
