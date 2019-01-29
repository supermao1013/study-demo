package com.dalomao.thread.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * <p>Package: com.dalomao.demo.thread.concurrent</p>
 * <p>Description:Exchange主要用来两个线程间交换数据 </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/11
 **/
public class ExchangerDemo {
    static final Exchanger<List<String>> exgr = new Exchanger<>();

    public static void main(String[] args) {

        //线程1
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    List<String> list = new ArrayList<>();
                    list.add(Thread.currentThread().getId()+" insert A1");
                    list.add(Thread.currentThread().getId()+" insert A2");
                    list = exgr.exchange(list);//同步点，交换数据
                    for(String item:list){
                        System.out.println(Thread.currentThread().getId()+":"+item);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //线程2
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    List<String> list = new ArrayList<>();
                    list.add(Thread.currentThread().getId()+" insert B1");
                    list.add(Thread.currentThread().getId()+" insert B2");
                    list.add(Thread.currentThread().getId()+" insert B3");
                    System.out.println(Thread.currentThread().getId()+" will sleep");
                    Thread.sleep(1500);
                    list = exgr.exchange(list);//同步点，交换数据
                    for(String item:list){
                        System.out.println(Thread.currentThread().getId()+":"+item);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
