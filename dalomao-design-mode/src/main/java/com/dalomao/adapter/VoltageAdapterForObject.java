package com.dalomao.adapter;

/**
 * <p>Package: com.dalomao.demo.adapter</p>
 * <p>Description:对象适配器（常用）：持有src，然后实现dst接口 </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/7
 **/
public class VoltageAdapterForObject implements Voltage5 {

    //持有src
    private Voltage220 mVoltage220;

    public VoltageAdapterForObject(Voltage220 voltage220) {
        mVoltage220 = voltage220;
    }

    @Override
    public int output5V() {
        int dst = 0;
        if (null != mVoltage220) {
            int src = mVoltage220.output220V();
            System.out.println("对象适配器工作，开始适配电压");
            dst = src / 44;
            System.out.println("适配完成后输出电压：" + dst);
        }
        return dst;
    }
}
