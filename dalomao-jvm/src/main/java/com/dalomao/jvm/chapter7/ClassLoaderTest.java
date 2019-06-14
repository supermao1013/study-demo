package com.dalomao.jvm.chapter7;

import java.io.IOException;
import java.io.InputStream;

/**
 * 类加载器不同演示
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //自定义类加载器
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";

                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }


                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object obj = myLoader.loadClass("com.dalomao.jvm.chapter7.ClassLoaderTest").newInstance();

        System.out.println(obj.getClass());//class com.dalomao.jvm.chapter7.ClassLoaderTest

        //myLoader和虚拟机的Loader不同的类加载器，导致尽管类相同，但是instanceof却不一致
        System.out.println(obj instanceof com.dalomao.jvm.chapter7.ClassLoaderTest);//false
    }
}
