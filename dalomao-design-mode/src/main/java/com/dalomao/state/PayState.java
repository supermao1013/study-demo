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
public class PayState implements State {

    private NewCoffeeMachine machine;
    public PayState(NewCoffeeMachine machine) {
        this.machine = machine;
    }

    @Override
    public void pay() {
        System.out.println("您已支付，请去确定购买！");
    }

    @Override
    public void refund() {
        System.out.println("退款成功，请收好！");
        this.machine.state = this.machine.NO_PAY;
    }

    @Override
    public void buy() {
        System.out.println("购买成功，请取用");
        this.machine.state = this.machine.SOLD;
    }

    @Override
    public void getCoffee() {
        System.out.println("请先确定购买！");
    }
}
