package com.exji.juc.synch;

public class SynchTest2 {
    public static Object lock = new Object();
    public static void main(String[] args) {
        synchronized (lock){
            try {
                lock.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
