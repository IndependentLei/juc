package com.exji.juc.synch;

public class SynchTest1 {
    private static Long count = 0L;
    private static Object obj = new Object();

    public static void main(String[] args) {
        new Thread(()->{
            for (int i = 0; i < 5000; i++) {
                synchronized (obj){
                    count++;
                }
            }
        }).start();

        new Thread(()->{
            for (int i = 0; i < 5000; i++) {
                synchronized (obj){
                    count--;
                }
            }
        }).start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("最终的结果为:"+count);
    }
}
