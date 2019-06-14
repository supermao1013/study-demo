package com.dalomao.jvm.chapter3;

/**
 * JVM是否使用引用计数法测试
 * 结果：JVM不使用引用计数法
 */
public class ReferenceCountGC {
    public Object instance = null;

    private static final int _1MB = 1024 * 1024;

    private byte[] bigSize = new byte[2 * _1MB];

    public static void main(String[] args) {
        ReferenceCountGC objA = new ReferenceCountGC();
        ReferenceCountGC objB = new ReferenceCountGC();
        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        //打印堆栈信息需设置参数：-XX:+PrintGCDetails
        //objA和objB会被回收掉，因此jvm并不是使用引用计数法，而是使用GC Roots可达性分析算法
        System.out.println("我还活着");
        System.gc();
        System.out.println("我死了");
    }
}
