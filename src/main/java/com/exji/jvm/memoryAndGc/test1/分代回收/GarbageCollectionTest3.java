package com.exji.jvm.memoryAndGc.test1.分代回收;

public class GarbageCollectionTest3 {
    public static void main(String[] args) {
        /**
         * 垃圾回收器
         *
         * 1.串行
         *      .单线程
         *      ·堆内存较小，适合个人电脑
         *
         * 2.吞吐量优先
         *      .多线程
         *      .堆内存较大，多核cpu
         *      .让单位时间内,STW的时间最短
         *
         * 3.响应时间优先
         *      .多线程
         *      .堆内存较大，多核cpu
         *      .尽可能让STW的时间最短
         */

        /**
         * 串行垃圾回收期
         * -XX：+UseSerialGC=Serial + SerialOld
         *                   复制      标记整理
         */
    }
}
