package com.dalomao.tempmethod;

/**
 * <p>Package: com.dalomao.demo.tempmethod</p>
 * <p>Description:模板抽象类 </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/7
 **/
public abstract class AbstractClass {
    //让子类去实现特殊的处理逻辑
    protected abstract void doAnything();
    protected abstract void doSomething();

    //模板方法，定义成final，不让子类修改
    public final void templateMethod(){
        /*
         * 调用基本方法，完成相关的逻辑
         */
        this.doAnything();
        this.doSomething();
    }
}
