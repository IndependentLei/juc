package com.exji.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest2 {

    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {

            try {
                if (!lock.tryLock(2,TimeUnit.SECONDS)) {  // 尝试获取锁，等待两秒
                    System.out.println("t1没有所有获取锁1");
                    return;
                }
            } catch (InterruptedException e) {
                System.out.println("t1没有所有获取锁1");
                return;
            }

            try {
                System.out.println("t1获取到了锁");
            } finally {
                lock.unlock();
            }
        }, "t1");


        try {
            lock.lock();
            System.out.println("main 线程加锁");
            t1.start();
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
            System.out.println("main 线程释放了锁");
        }



    }
}
