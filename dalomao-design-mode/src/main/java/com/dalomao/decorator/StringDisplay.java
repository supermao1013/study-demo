package com.dalomao.decorator;

/**
 * <p>Package: com.dalomao.demo.decorator</p>
 * <p>Description:被装饰的对象 </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/4
 **/
public class StringDisplay extends Display {

    //要显示的字符串
    private String string;
    public StringDisplay(String string) {
        this.string = string;
    }

    @Override
    int getColumns() {
        return string.getBytes().length; //字符数
    }

    @Override
    int getRows() {
        return 1;   //行数为1
    }

    @Override
    String getRowText(int row) {
        if (row == 0) {
            return string;//仅当row为0时返回值
        }
        return null;
    }
}
