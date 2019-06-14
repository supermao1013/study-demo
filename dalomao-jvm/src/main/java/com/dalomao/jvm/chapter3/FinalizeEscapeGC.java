package com.dalomao.jvm.chapter3;

/**
 * finalize方法尝试在垃圾回收的最后阶段拯救自己
 *
 * 注意：
 *  1.任何一个对象的finalize()方法都只会被系统自动调用一次，若对象面临下一次回收，它的finalize方法不会再次被执行
 *  2.在代码编写中，不建议重写finalize()方法
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("i am still alive");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();

        //对象第一次成功拯救自己
        SAVE_HOOK = null;
        System.gc();
        //finalize方法优先级比较低，这里先暂停0.5s等待它
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("i am dead");
        }

        //对象第二次尝试拯救，最终失败（代码和上面一样）
        SAVE_HOOK = null;
        System.gc();
        //finalize方法优先级比较低，这里先暂停0.5s等待它
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("i am dead");
        }
    }
}
