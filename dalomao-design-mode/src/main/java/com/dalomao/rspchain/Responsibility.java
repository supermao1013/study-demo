package com.dalomao.rspchain;

/**
 * <p>Package: com.dalomao.demo.rspchain</p>
 * <p>Description:责任接口 </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/4
 **/
public interface Responsibility {
    void process(Request request, ResponsibilityChain chain);
}
