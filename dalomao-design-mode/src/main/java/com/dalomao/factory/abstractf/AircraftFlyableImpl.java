package com.dalomao.factory.abstractf;

/**
 * <p>Package: com.dalomao.demo.factory.abstractf</p>
 * <p>Description:TODO </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/2
 **/
public class AircraftFlyableImpl implements Flyable{
    @Override
    public void fly(int height) {
        System.out.println("抽象工厂模式：我是一架客运机，目前飞行高度为" + height);
    }
}
