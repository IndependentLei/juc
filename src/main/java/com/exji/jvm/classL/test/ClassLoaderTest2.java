package com.exji.jvm.classL.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ClassLoaderTest2 {
    public static void main(String[] args) throws Exception{
        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> h = myClassLoader.loadClass("H2");
//        Class<?> h1 = myClassLoader.loadClass("H");
//        System.out.println(h == h1);
    }
}

class MyClassLoader extends ClassLoader{
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // E:\workspace\back\juc\target\classes\com\exji\jvm\classL\test
        // D:\
        try {
            String path = "D:\\"+name+".class";
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            Files.copy(Paths.get(path),bos);
            byte[] bytes = bos.toByteArray();
            return defineClass(name,bytes,0,bytes.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
