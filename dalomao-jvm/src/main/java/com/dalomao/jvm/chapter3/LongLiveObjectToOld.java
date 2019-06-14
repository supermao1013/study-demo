package com.dalomao.jvm.chapter3;

/**
 * 长期存活的对象进入老年代
 */
public class LongLiveObjectToOld {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        //测试前设置VM参数：-Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1
        //堆大小设置为20M，年轻代设置为10M，对象晋升到老年代的年龄阈值MaxTenuringThreshold=1
        //每一次MinorGC，对象的年龄就+1，可以分别设置MaxTenuringThreshold=1和MaxTenuringThreshold=15进行测试
        byte[] allocation1,allocation2,allocation3,allocation4;

        allocation1 = new byte[_1MB / 4];
        //什么时候进入老年代取决于 XX:MaxTenuringThreshold设置
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];

        allocation3 = null;

        allocation3 = new byte[4 * _1MB];
    }
}
