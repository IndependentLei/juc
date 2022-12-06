package com.exji.juc.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

public class AtomicTest4 {
    static LongAdder i = new LongAdder();
    static int  r = 0;
    public static void main(String[] args) {

//        for (int j = 0; j < 4; j++) {
//            new Thread(()->{
//                for (int k = 0; k < 10000; k++) {
//                    i.add(1);
//                }
//            }).start();
//        }

        for (int j = 0; j < 4; j++) {
            new Thread(()->{
                for (int k = 0; k < 10000; k++) {
                    r++;
                }
            }).start();
        }

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println(i.intValue()); // 1160  0  6203
        System.out.println(r); // 1160  0  6203
    }
}
