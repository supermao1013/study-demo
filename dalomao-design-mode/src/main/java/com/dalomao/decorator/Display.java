package com.dalomao.decorator;

/**
 * <p>Package: com.dalomao.demo.decorator</p>
 * <p>Description:被装饰的对象的接口 </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/4
 **/
public abstract class Display {
    abstract int getColumns();//获取横向字符数
    abstract int getRows();//获取纵向字符数
    abstract String getRowText(int i);//获取第row行的字符串

    /**
     * 显示所有字符
     */
    public final void show() {
        for (int i=0; i<getRows(); i++) {
            System.out.println(getRowText(i));
        }
    }
}
