package com.dalomao.thread.pipeline;

import java.io.IOException;
import java.io.PipedOutputStream;
import java.io.PipedWriter;

/**
 * <p>Package: com.dalomao.demo.thread.pipeline</p>
 * <p>Description:通过管道写出数据 </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2019/1/27
 **/
public class WriteData {

    /**
     * 通过管道写字节流
     * @param out
     */
    public void writeMethodByByte(PipedOutputStream out) {
        try {
            System.out.println("write :");
            for (int i=0; i<300; i++) {
                String outData = "" + (i + 1);
                out.write(outData.getBytes());
                System.out.print(outData);
            }

            System.out.println();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过管道写字符流
     * @param out
     */
    public void writeMethodByCharacter(PipedWriter out) {
        try {
            System.out.println("write :");
            for (int i=0; i<300; i++) {
                String outData = "" + (i + 1);
                out.write(outData);
                System.out.print(outData);
            }

            System.out.println();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
