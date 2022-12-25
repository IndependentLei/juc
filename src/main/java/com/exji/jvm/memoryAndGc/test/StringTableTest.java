package com.exji.jvm.memoryAndGc.test;

public class StringTableTest {
    /**
     * 二进制字节码(类基本信息，常量池，类方法定义，包含了虚拟机指令)
     * @param args
     */
    public static void main(String[] args) {
        // 运行时常量池
        System.out.println("hello world!");
        /**
         * ·常量池，就是一张表，虚拟机指令根据这张常量表找到要执行的类名、方法名、参数类型、字面量等信息
         * ·运行时常量池，常量池是*.clss文件中的，当该类被加载，它的常量池信息就会放入运行时常量池，并
         * 把里面的符号地址变为真实地址
         */
    }
}
