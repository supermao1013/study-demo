package com.dalomao.facade;

/**
 * <p>Package: com.dalomao.demo.facade</p>
 * <p>Description:门面 </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/7
 **/
public class Computer {
    private CPU cpu;
    private Disk disk;
    private Memory memory;

    public Computer() {
        cpu = new CPU();
        disk = new Disk();
        memory = new Memory();
    }

    public void start() {
        System.out.println("computer start begin");
        cpu.start();
        disk.start();
        memory.start();
        System.out.println("computer start end");
    }

    public void shutdown() {
        System.out.println("computer shutdown begin");
        cpu.shutdown();
        disk.shutdown();
        memory.shutdown();
        System.out.println("computer shutdown end");
    }
}
