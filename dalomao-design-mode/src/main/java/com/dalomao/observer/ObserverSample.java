package com.dalomao.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * <p>Package: com.dalomao.demo.observer</p>
 * <p>Description:TODO </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/7
 **/
public class ObserverSample {
    public static void main(String[] args) {
        //新建一个主题对象，并且重写通知方法
        Observable subject1 = new Observable() {
            public synchronized void notifyObservers(Object data) {
                setChanged();
                super.notifyObservers(data);
            }
        };

        //往主题对象中添加观察者
        subject1.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                System.out.println("观察者1收到通知被更新了..." + arg);
            }
        });
        subject1.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                System.out.println("观察者2收到通知被更新了..." + arg);
            }
        });

        //发送通知，内部是同步并循环调用每一个观察者
        subject1.notifyObservers("change1");
        subject1.notifyObservers("change2");
    }
}
