package com.dalomao.thread.base;

/**
 * <p>Package: com.dalomao.demo.thread.base</p>
 * <p>Description:线程本地变量</p>
 * 允许每个线程存放自己的值，做到隔离性。内部是Map结构，key为每一个线程的线程ID
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/10
 **/
public class ThreadLocalDemo {
    private  static  ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
        public Integer initialValue(){
            return 0;
        }
    };

    class SequenceNumberRandom {
        public int getNextNum() {
            int m = threadLocal.get() + 1;
            threadLocal.set(m);
            return threadLocal.get();
        }
    }

    class Client extends Thread {
        private SequenceNumberRandom r;

        public Client(SequenceNumberRandom r) {
            this.r = r;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " : "
                        + r.getNextNum());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalDemo demo = new ThreadLocalDemo();
        SequenceNumberRandom r = demo.new SequenceNumberRandom();

        Client c1 = demo.new Client(r);
        Client c2 = demo.new Client(r);
        Client c3 = demo.new Client(r);
        Client c4 = demo.new Client(r);

        c1.start();
        c2.start();
        c3.start();
        c4.start();
    }
}
