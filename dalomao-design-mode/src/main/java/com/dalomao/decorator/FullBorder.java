package com.dalomao.decorator;

/**
 * <p>Package: com.dalomao.demo.decorator</p>
 * <p>Description:装饰类，主要装饰上下边框 </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/4
 **/
public class FullBorder extends Border {

    public FullBorder(Display display) {
        super(display);
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
        return 1 + display.getRows() + 1;
    }

    /**
     * 指定的那一行的字符串
     * @param i
     * @return
     */
    @Override
    String getRowText(int i) {
        if (i == 0) {
            return "+" + makeLine('-', display.getColumns()) + "+";//下边框
        } else if (i == display.getRows()+1) {
            return "+" + makeLine('-', display.getColumns()) + "+";//上边框
        } else {
            return "|" + display.getRowText(i - 1) + "|";//其他边框
        }
    }

    /**
     * 生成一个重复count次的字符ch的字符串
     * @param ch
     * @param count
     * @return
     */
    private String makeLine(char ch, int count) {
        StringBuffer buf = new StringBuffer();
        for (int i=0; i<count; i++) {
            buf.append(ch);
        }

        return buf.toString();
    }
}
