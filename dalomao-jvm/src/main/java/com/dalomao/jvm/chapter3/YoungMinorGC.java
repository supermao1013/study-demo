package com.dalomao.jvm.chapter3;

/**
 * 年轻代MinorGC场景
 */
public class YoungMinorGC {
    private static final int _1MB = 1024 * 1024;


    public static void main(String[] args) {
        //测试前设置VM参数：-Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
        //堆大小设置为20M，年轻代设置为10M，然后调试运行
        byte[] allocation1,allocation2,allocation3,allocation4;

        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];//这里会发生MinorGC
    }
}
