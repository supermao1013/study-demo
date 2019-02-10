package com.dalomao.tempmethod;

/**
 * <p>Package: com.dalomao.demo.tempmethod</p>
 * <p>Description:TODO </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/7
 **/
public class ConcreteClass2 extends AbstractClass{
    @Override
    protected void doAnything() {
        //子类实现具体
        System.out.println("ConcreteClass2 doAnything");
    }

    @Override
    protected void doSomething() {
        System.out.println("ConcreteClass2 doSomething");
    }
}
