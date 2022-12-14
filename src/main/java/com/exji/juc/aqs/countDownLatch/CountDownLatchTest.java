package com.exji.juc.aqs.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    public static void main(String[] args) {

        // 相当于倒计时
        CountDownLatch countDownLatch = new CountDownLatch(5);

        new Thread(()->{
            countDownLatch.countDown();
        }).start();
        new Thread(()->{
            countDownLatch.countDown();
        }).start();
        new Thread(()->{
            countDownLatch.countDown();
        }).start();
        new Thread(()->{
            countDownLatch.countDown();
        }).start();

        new Thread(()->{
            countDownLatch.countDown();
        }).start();


        try {
            countDownLatch.await();
            System.out.println("go----->");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
