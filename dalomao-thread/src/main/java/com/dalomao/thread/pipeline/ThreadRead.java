package com.dalomao.thread.pipeline;

import java.io.PipedInputStream;

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
public class ThreadRead extends Thread {
    private ReadData read;
    private PipedInputStream input;

    public ThreadRead(ReadData read, PipedInputStream input) {
        super();
        this.read = read;
        this.input = input;
    }

    public void run() {
        read.readMethodByByte(input);
    }
}
