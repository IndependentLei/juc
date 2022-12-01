package com.exji.juc.atomic;

import com.fasterxml.jackson.databind.util.JSONPObject;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.util.StopWatch;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest1 {
    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch("test");
        stopWatch.start("11");
        Account account = new UnsafeAccount(10000);
        Account.test(account);   // 有锁
//        Account account = new CasAccount(10000);
//        Account.test(account);  无锁
        stopWatch.stop();
        System.out.println(stopWatch.toString());
        System.out.println(account.balance());

    }
}

class CasAccount implements Account{
    private AtomicInteger money;

    public CasAccount(Integer money) {
        this.money = new AtomicInteger(money);
    }

    @Override
    public void withdrawal(Integer money) {
        // cas比较并替换,每次减10
        while (true) {
            int pre = this.money.get();
            int next = pre - money;
            if(this.money.compareAndSet(pre, next)){
                break;
            }
        }
    }

    @Override
    public Integer balance() {
        return this.money.intValue();
    }
}

class UnsafeAccount implements Account{
    private Integer money;

    public UnsafeAccount(Integer money) {
        this.money = money;
    }

    @Override
    public synchronized void withdrawal(Integer money) {
        this.money -= money;
    }

    @Override
    public synchronized Integer balance() {
        return this.money;
    }
}

interface Account{
    void withdrawal(Integer money);
    Integer balance();

    static void test(Account account){
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            threadList.add(new Thread(()->account.withdrawal(10)));
        }

        threadList.forEach(Thread::start);

        threadList.forEach(t1->{
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
