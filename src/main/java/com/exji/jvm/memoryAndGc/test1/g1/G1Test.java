package com.exji.jvm.memoryAndGc.test1.g1;

public class G1Test {
    public static void main(String[] args) {
        /**
         * 定义：Garbage First
         *  ■2004论文发布
         *  ■2009JDK6u14体验
         *  ■2012DK7u4官方支持
         *  ■2017DK9默认
         * 适用场景
         *      ·同时注重吞吐量(Throughput)和低延迟(Low latency),默认的暂停目标是200ms
         *      ·超大堆内存，会将堆划分为多个大小相等的Region
         *      ·整体上是标记+整理算法，两个区域之间是复制算法
         * 相关JVM参数
         * -XX:+UseG1GC
         * -XX:G1HeapRegionSize=size
         * -XX:MaxGCPauseMillis=time
         */

        /**
         * 5)Full GC
         *
         * SerialGC
         * ·新生代内存不足发生的垃圾收集-minor gc
         * ·老年代内存不足发生的垃圾收集-gc
         *
         * ParallelGC
         * ·新生代内存不足发生的垃圾收集-minor gc
         * ·老年代内存不足发生的垃圾收集-full gc
         *
         * CMS
         * ·新生代内存不足发生的垃圾收集-minor gc
         * ·老年代内存不足
         *
         * G1
         * ·新生代内存不足发生的垃圾收集-minor gc
         * ·老年代内存不足
         */
    }
}
