package com.dalomao.strategy;

/**
 * <p>Package: com.dalomao.demo.strategy</p>
 * <p>Description:TODO </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/1
 **/

/**
 * 订单处理接口，不同订单有不同的处理
 */
public interface OrderHandler {
    boolean support(String orderType);

    void handler();
}
