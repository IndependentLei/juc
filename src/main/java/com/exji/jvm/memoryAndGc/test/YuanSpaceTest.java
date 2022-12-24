package com.exji.jvm.memoryAndGc.test;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * 元空间 内存溢出
 * -XX:MaxMetaspaceSize=8m
 */
public class YuanSpaceTest extends ClassLoader{
    public static void main(String[] args) {
        int j = 0;
        try {
            YuanSpaceTest yuanSpaceTest = new YuanSpaceTest();
            for (int i = 0; i < 10000; i++,j++) {
                // ClassWriter 作用是生成类的二进制字节码
                ClassWriter cw = new ClassWriter(0);
                // 版本号，public，类名，包名，类的父类，实现的接口
                cw.visit(Opcodes.V1_8,Opcodes.ACC_PUBLIC,"Class"+i,null,"java/lang/Object",null);
                // 生成类的字节码
                byte[] bytes = cw.toByteArray();
                // 加载类,返回类对象
                Class<?> aClass = yuanSpaceTest.defineClass("Class" + i, bytes, 0, bytes.length);
            }
        }finally {
            // Error occurred during initialization of VM
            // MaxMetaspaceSize is too small.
            System.out.println(j);
        }
    }
}
