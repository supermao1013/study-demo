package com.dalomao.proxy.dynamic.jdk;

/**
 * Created by maohw on 2018/8/29.
 */
public class TeacherService implements IPepole {
    @Override
    public void eat() {
        System.out.println("TeacherService eat");
    }

    @Override
    public void career() {
        System.out.println("TeacherService career");
    }
}
