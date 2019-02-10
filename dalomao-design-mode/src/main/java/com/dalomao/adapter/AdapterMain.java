package com.dalomao.adapter;

/**
 * <p>Package: com.dalomao.demo.adapter</p>
 * <p>Description:TODO </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/7
 **/
public class AdapterMain {
    public static void main(String[] args) {
        //类适配器测试
        VoltageAdapterForClass adapterForClass = new VoltageAdapterForClass();
        adapterForClass.output5V();

        //对象适配器测试
        VoltageAdapterForObject adapterForObject = new VoltageAdapterForObject(new Voltage220());
        adapterForObject.output5V();
    }

}
