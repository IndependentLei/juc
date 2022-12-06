package com.exji.juc.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class MyAtomicInteger {
    private volatile int value;
    private static final long valueOffset;
    private static final Unsafe myUnSafe;
    static {
        myUnSafe = getMyUnSafe();
        try {
            valueOffset = myUnSafe.objectFieldOffset(MyAtomicInteger.class.getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public MyAtomicInteger(Integer value) {
        this.value = value;
    }

    public static Unsafe getMyUnSafe() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            return (Unsafe) theUnsafe.get(null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer get(){
        return this.value;
    }
    public void increment(int value){
        while (true){
            int prev = this.value;
            int next = value + prev;
            if(myUnSafe.compareAndSwapInt(this,valueOffset,prev,next)){
                break;
            }
        }
    }
}
