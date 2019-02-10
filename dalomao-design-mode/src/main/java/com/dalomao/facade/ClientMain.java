package com.dalomao.facade;

/**
 * <p>Package: com.dalomao.demo.facade</p>
 * <p>Description:客户角色 </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/7
 **/
public class ClientMain {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.start();
        computer.shutdown();
    }
}
