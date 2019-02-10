package com.dalomao.decorator;

/**
 * <p>Package: com.dalomao.demo.decorator</p>
 * <p>Description:装饰类，主要装饰左右边框 </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/4
 **/
public class SiderBorder extends Border {

    //装饰边框的字符
    private char borderChar;
    public SiderBorder(Display display, char ch) {
        super(display);
        borderChar = ch;
    }

    /**
     * 字符数为字符串字符数加上两侧边框字符数
     * @return
     */
    @Override
    int getColumns() {
        return 1 + display.getColumns() + 1;
    }

    /**
     * 行数即被装饰物的行数
     * @return
     */
    @Override
    int getRows() {
        return display.getRows();
    }

    /**
     * 指定的那一行的字符串为被装饰物的字符串加上两侧边框的字符
     * @param i
     * @return
     */
    @Override
    String getRowText(int i) {
        return borderChar + display.getRowText(i) + borderChar;
    }
}
