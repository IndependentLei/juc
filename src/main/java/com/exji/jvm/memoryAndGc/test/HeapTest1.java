package com.exji.jvm.memoryAndGc.test;

import java.util.concurrent.TimeUnit;

public class HeapTest1 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("1=========>");
        TimeUnit.SECONDS.sleep(30);
        byte[] array = new byte[1024*10240*10];
        System.out.println("2=============>");
        TimeUnit.SECONDS.sleep(30);
        array = null;
        System.gc();
        System.out.println("3=========>");
        TimeUnit.SECONDS.sleep(100);
    }
}
