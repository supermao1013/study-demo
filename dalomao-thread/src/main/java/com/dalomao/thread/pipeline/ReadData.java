package com.dalomao.thread.pipeline;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedReader;

/**
 * <p>Package: com.dalomao.demo.thread.pipeline</p>
 * <p>Description:通过管道读数据 </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2019/1/27
 **/
public class ReadData {

    /**
     * 通过管道流读字节流
     * @param input
     */
    public void readMethodByByte(PipedInputStream input) {
        try {
            System.out.println("read :");
            byte[] byteArr = new byte[20];
            int readLength = input.read(byteArr);
            while (readLength != -1) {
                String newData =  new String(byteArr, 0, readLength);
                System.out.print(newData);
                readLength = input.read(byteArr);
            }

            System.out.println();
            input.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过管道流读字符流
     * @param input
     */
    public void readMethodByCharacter(PipedReader input) {
        try {
            System.out.println("read :");
            char[] charArr = new char[20];
            int readLength = input.read(charArr);
            while (readLength != -1) {
                String newData =  new String(charArr, 0, readLength);
                System.out.print(newData);
                readLength = input.read(charArr);
            }

            System.out.println();
            input.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
