package com.dalomao.strategy;

import org.springframework.stereotype.Component;

/**
 * <p>Package: com.dalomao.demo.strategy</p>
 * <p>Description:电脑订单处理类 </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/1
 **/
@Component
public class ComputerOrderHandlerImpl implements OrderHandler {

    public static String ORDER_TYPE = "computer";

    @Override
    public boolean support(String orderType) {
        return orderType.equals(ComputerOrderHandlerImpl.ORDER_TYPE);
    }

    @Override
    public void handler() {
        System.out.println("策略模式：调用ComputerOrderHandler进行处理");
    }
}
