package com.dalomao.decorator;

/**
 * <p>Package: com.dalomao.demo.decorator</p>
 * <p>Description:装饰类的接口 </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/4
 **/
public abstract class Border extends Display {
    protected Display display;//被装饰对象，装饰类必须持有该对象
    protected Border(Display display) {
        this.display = display;
    }
}
