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
public class NoPayState implements State {

    private NewCoffeeMachine machine;
    public NoPayState(NewCoffeeMachine machine) {
        this.machine = machine;
    }

    @Override
    public void pay() {
        System.out.println("支付成功，请去确定购买咖啡。");
        this.machine.state = this.machine.PAY;
    }

    @Override
    public void refund() {
        System.out.println("你尚未支付，请不要乱按！");
    }

    @Override
    public void buy() {
        System.out.println("你尚未支付，请不要乱按！");
    }

    @Override
    public void getCoffee() {
        System.out.println("你尚未支付，请不要乱按！");
    }
}
