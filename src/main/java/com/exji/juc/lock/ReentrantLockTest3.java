package com.exji.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest3 {
    public static void main(String[] args) {

        // 筷子和吃饭问题 使用tryLock加锁解决问题
        Chopsticks c1 = new Chopsticks("1");
        Chopsticks c2 = new Chopsticks("2");
        Chopsticks c3 = new Chopsticks("3");
        Chopsticks c4 = new Chopsticks("4");
        Chopsticks c5 = new Chopsticks("5");

         new Person("小明",c1,c2).start();
         new Person("小孙",c2,c3).start();
         new Person("小李",c3,c4).start();
         new Person("小王",c4,c5).start();
         new Person("小强",c5,c1).start();
    }
}

class Person extends Thread{

    private String name;
    private Chopsticks left;
    private Chopsticks right;

    public Person(String name,Chopsticks left,Chopsticks right){
        this.name = name;
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (true) {
            if (right.tryLock()) {
                try {
                    if (left.tryLock()) {
                        try {
                            eat();
                        } finally {
                            left.unlock();
                        }
                    }
                } finally {
                    right.unlock();
                }
            }
        }
    }

    public void eat(){
        try {
            System.out.println(name+"-----------> 吃到饭啦");
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


class Chopsticks extends ReentrantLock{
    private String name;

    public Chopsticks(String name){
        this.name = name;
    }
}
