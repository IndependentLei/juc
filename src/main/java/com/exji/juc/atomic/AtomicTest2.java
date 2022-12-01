package com.exji.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest2 {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();
    }
}
