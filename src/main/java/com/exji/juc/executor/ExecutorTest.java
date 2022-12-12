package com.exji.juc.executor;

import java.util.concurrent.*;

public class ExecutorTest {
    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
        Future<Boolean> submit = pool.submit(() -> {
            int i = 1 / 0;
            return true;
        });
        try {
            System.out.println(submit.get());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    public static void test(){
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
        // 延时执行
        pool.schedule(()->{
            System.out.println("task1");
        },1, TimeUnit.SECONDS);

        // 延时执行
        pool.schedule(()->{
            System.out.println("task2");
        },1, TimeUnit.SECONDS);


        // 定时任务
        pool.scheduleAtFixedRate(()->{
            System.out.println("task3");
        },1,1,TimeUnit.SECONDS);

        pool.scheduleWithFixedDelay(()->{
            System.out.println("task4");
        },1,1,TimeUnit.SECONDS);
    }
}
