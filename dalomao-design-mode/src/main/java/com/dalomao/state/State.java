package com.dalomao.state;

/**
 * <p>Package: com.dalomao.demo.state</p>
 * <p>Description:TODO </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/9
 **/
public interface State {
    void pay();
    void refund();
    void buy();
    void getCoffee();
}
