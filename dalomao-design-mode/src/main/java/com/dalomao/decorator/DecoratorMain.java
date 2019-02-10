package com.dalomao.decorator;

/**
 * <p>Package: com.dalomao.demo.decorator</p>
 * <p>Description:装饰模式测试 </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/4
 **/
public class DecoratorMain {
    public static void main(String[] args) {
        //依次不断地装饰
        Display b1 = new StringDisplay("Hello,world.");
        Display b2 = new SiderBorder(b1, '#');
        Display b3 = new FullBorder(b2);
        b1.show();
        b2.show();
        b3.show();

        //循环装饰
        Display b4 = new SiderBorder(
                new FullBorder(
                        new FullBorder(
                                new SiderBorder(
                                        new FullBorder(
                                                new StringDisplay("你好，世界。")
                                        ),
                                        '*'
                                )
                        )
                ),
                '/'
        );
        b4.show();
    }

}
