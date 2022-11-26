package com.exji.juc.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {

        // 可重入锁
        lock.lock();
        try{
            System.out.println("main lock");
            m1();
        }finally {
            lock.unlock();
        }
    }

    public static void m1(){
        lock.lock();
        try{
            System.out.println("m1 lock");
        }finally {
            lock.unlock();
        }
    }
}
