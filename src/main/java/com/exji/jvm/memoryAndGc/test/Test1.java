package com.exji.jvm.memoryAndGc.test;

public class Test1 {
    public static void main(String[] args) {
        // 程序计数器和虚拟机栈 是每个线程 独有的
        // 虚拟机栈 的使用，每个方法都被压入栈中，执行完，然后被弹出
        // 设置栈大小 -Xss8m
        method1();
    }

    public static void method1(){
        int c = method02(1, 3);
    }

    public static int method02(int a,int b){
        int c = Math.max(a,b);
        return c;
    }
}
