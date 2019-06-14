package com.dalomao.jvm.chapter3;

/**
 * 大对象直接进入老年代的场景
 */
public class BigObjectToOld {
    private static final int _1MB = 1024 * 1024;


    public static void main(String[] args) {
        //测试前设置VM参数：-Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728 -XX:+UseConcMarkSweepGC
        //堆大小设置为20M，年轻代设置为10M
        //-XX:PretenureSizeThreshold=3145728设置为新对象大小超出3M则直接进入老年代，这里的参数不能像-Xmx之类的参数直接写成3M
        //PretenureSizeThreshold参数只适合Serial和ParNew两款收集器有效
        byte[] allocation;

        allocation = new byte[4 * _1MB];//大对象会直接进入到老年代
    }
}
