package com.dalomao.rspchain;

/**
 * <p>Package: com.dalomao.demo.rspchain</p>
 * <p>Description:责任A </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/5
 **/
public class ResponsibilityB implements Responsibility {
    @Override
    public void process(Request request, ResponsibilityChain chain) {
        System.out.println("Responsibility-B do something...");
        chain.process(request);
    }
}
