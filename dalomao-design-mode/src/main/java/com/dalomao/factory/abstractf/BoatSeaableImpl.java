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
public class BoatSeaableImpl implements Seaable{
    @Override
    public void move(int speed) {
        System.out.println("抽象工厂模式：我是一艘轮船，目前形势速度为" + speed);
    }
}
