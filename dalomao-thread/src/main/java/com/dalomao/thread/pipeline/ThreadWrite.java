package com.dalomao.thread.pipeline;

import java.io.PipedOutputStream;

/**
 * <p>Package: com.dalomao.demo.thread.pipeline</p>
 * <p>Description:TODO </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2019/1/27
 **/
public class ThreadWrite extends Thread {
    private WriteData write;
    private PipedOutputStream out;

    public ThreadWrite(WriteData write, PipedOutputStream out) {
        super();
        this.write = write;
        this.out = out;
    }

    public void run() {
        write.writeMethodByByte(out);
    }
}
