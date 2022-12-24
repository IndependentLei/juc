package com.exji.jvm.memoryAndGc.test;

import java.util.ArrayList;
import java.util.List;

public class HeapTest {
    public static void main(String[] args) {

        /**
         * Heap堆
         * ·通过new关键字，创建对象都会使用堆内存
         * 特点
         * ·它是线程共享的，堆中对象都需要考虑线程安全的问题
         * ·有垃圾回收机制
         *
         * 设置堆大小 -Xmx8m
         */
        String testStr = "hello";
        List<String> testList = new ArrayList<>();
        int i = 1;
        try {
            while (true){
                testList.add(testStr);
                testStr = testStr + testStr;
                i++;
            }
        } catch (Throwable e) {
            //  java.lang.OutOfMemoryError: Java heap space
            System.out.println(i); // 27
        }
    }
}
