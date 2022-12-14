package com.exji.juc.aqs.countDownLatch;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest1 {
    public static void main(String[] args) {
        ExecutorService exectors = Executors.newFixedThreadPool(10);

        String[] all = new String[10];

        CountDownLatch countDownLatch = new CountDownLatch(10);

        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int k = i;
            exectors.execute(()->{
                for (int j = 0; j <=100; j++) {
                    try {
                        Thread.sleep(random.nextInt(100));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    all[k] = j + "%";
                    System.out.print("\r"+ Arrays.toString(all));
                }
                countDownLatch.countDown();
            });
        }

        try {
            countDownLatch.await();
            System.out.println();
            System.out.println("游戏开始");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        exectors.shutdown();
    }
}
