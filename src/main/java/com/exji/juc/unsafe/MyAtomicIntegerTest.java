package com.exji.juc.unsafe;

import java.util.concurrent.TimeUnit;

public class MyAtomicIntegerTest {
    public static void main(String[] args) {
        MyAtomicInteger myAtomicInteger = new MyAtomicInteger(0);

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                for (int j = 0; j < 100000; j++) {
                    myAtomicInteger.increment(1);
                }
            }).start();
        }

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(myAtomicInteger.get());
    }
}
