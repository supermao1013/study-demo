package com.dalomao.factory.abstractf;

/**
 * <p>Package: com.dalomao.demo.factory.abstractf</p>
 * <p>Description:抽象工厂类 </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/2
 **/
public abstract class AbstractAbleFactory {

    public abstract Flyable createFlyable();

    public abstract Moveable createMoveable();

    public abstract Seaable createSeaable();
}
