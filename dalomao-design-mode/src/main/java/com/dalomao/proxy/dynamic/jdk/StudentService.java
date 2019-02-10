package com.dalomao.proxy.dynamic.jdk;

/**
 * Created by maohw on 2018/8/29.
 */
public class StudentService implements IPepole {
    @Override
    public void eat() {
        System.out.println("StudentService eat");
    }

    @Override
    public void career() {
        System.out.println("StudentService career");
    }
}
