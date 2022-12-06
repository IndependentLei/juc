package com.exji.juc.atomic;

public class AtomicTest3 {
    public static void main(String[] args) {
        /**
         *    // 伪共享   @sun.misc.Contended 将Cell对象之间的缓存行之间添加 120bit的距离 将 Cell数据中的cell对象放在 cpu缓存的 不同缓存行，一个核心改变值，不会使另外一个核心的缓存行全部失效
         *     @sun.misc.Contended static final class Cell {
         *         volatile long value;
         *         Cell(long x) { value = x; }
         *         final boolean cas(long cmp, long val) {
         *             return UNSAFE.compareAndSwapLong(this, valueOffset, cmp, val);
         *         }
         *
         *         // Unsafe mechanics
         *         private static final sun.misc.Unsafe UNSAFE;
         *         private static final long valueOffset;
         *         static {
         *             try {
         *                 UNSAFE = sun.misc.Unsafe.getUnsafe();
         *                 Class<?> ak = Cell.class;
         *                 valueOffset = UNSAFE.objectFieldOffset
         *                     (ak.getDeclaredField("value"));
         *             } catch (Exception e) {
         *                 throw new Error(e);
         *             }
         *         }
         *     }
         */
    }
}
