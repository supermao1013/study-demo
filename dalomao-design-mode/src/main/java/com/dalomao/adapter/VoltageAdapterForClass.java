package com.dalomao.adapter;

/**
 * <p>Package: com.dalomao.demo.adapter</p>
 * <p>Description:类适配器，继承src，实现dst接口 </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/7
 **/
public class VoltageAdapterForClass extends Voltage220 implements Voltage5 {

    @Override
    public int output5V() {
        int src = output220V();
        System.out.println("适配器工作开始适配电压");
        int dst = src / 44;
        System.out.println("适配完成后输出电压：" + dst);
        return dst;
    }
}
