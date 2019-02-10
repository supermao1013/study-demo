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
public class CarMoveableImpl implements Moveable{
    @Override
    public void run(int speed) {
        System.out.println("抽象工厂模式：我是一辆出租车，目前行驶速度为" + speed);
    }
}
