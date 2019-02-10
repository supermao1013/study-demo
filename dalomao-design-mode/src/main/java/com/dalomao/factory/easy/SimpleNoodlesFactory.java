package com.dalomao.factory.easy;

/**
 * <p>Package: com.dalomao.demo.factory.easy</p>
 * <p>Description:面馆（简单工厂类） </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/1
 **/
public class SimpleNoodlesFactory {
    public static final int TYPE_LZ = 1;//兰州拉面
    public static final int TYPE_PM = 2;//泡面
    public static final int TYPE_PT = 3;//莆田卤面

    public static Noodles createNoodles(int type) {
        switch (type) {
            case TYPE_LZ:
                return new LzNoodlesImpl();
            case TYPE_PM:
                return new PaoNoodlesImpl();
            case TYPE_PT:
            default:
                return new PutianNoodlesImpl();
        }
    }
}
