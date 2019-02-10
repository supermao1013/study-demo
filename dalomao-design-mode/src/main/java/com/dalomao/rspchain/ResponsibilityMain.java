package com.dalomao.rspchain;

/**
 * <p>Package: com.dalomao.demo.rspchain</p>
 * <p>Description:TODO </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/5
 **/
public class ResponsibilityMain {
    public static void main(String[] args) {
        ResponsibilityChain chain = new ResponsibilityChain();
        chain.register(new ResponsibilityA());
        chain.register(new ResponsibilityB());

        chain.process(new Request() {});
    }
}
