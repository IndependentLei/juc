package com.exji.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest1 {

    private static final ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {

        try {
            Thread t1 = new Thread(() -> {
                try {
                    // 可以被打断，不需要一直等待下去，
                    lock.lockInterruptibly();
                    System.out.println("获取锁了");
                } catch (InterruptedException e) {
                    System.out.println("没有获取锁，返回");
                    return;
                } finally {
                    lock.unlock();
                }
            }, "t1");



            lock.lock();

            TimeUnit.SECONDS.sleep(1);

            t1.start();

            t1.interrupt();  // 执行打断，t1线程被打断


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
