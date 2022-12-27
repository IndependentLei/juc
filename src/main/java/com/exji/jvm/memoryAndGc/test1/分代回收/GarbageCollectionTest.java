package com.exji.jvm.memoryAndGc.test1.分代回收;

public class GarbageCollectionTest {
    public static void main(String[] args) {
        /**
         * ·对象首先分配在伊甸园区域
         * ·新生代空间不足时，触发minor gc,伊甸园和from存活的对象使用copy复制到to中，存活的对象年龄加
         * 1并且交换from to
         * ·minor gc会引发stop the world,暂停其它用户的线程，等垃圾回收结束，用户线程才恢复运行
         * ·当对象寿命超过阈值时，会晋升至老年代，最大寿命是15(4bit)
         * ·当老年代空间不足，会先尝试触发minor gc,如果之后空间仍不足，那么触发full gc,STW时间更长
         */

        /**
         * 含义             参数
         * 堆初始大小            -Xms
         * 堆最大大小            -Xmx-XX:MaxHeapSize=size
         * 新生代大小            -Xmn(-XX:NewSize=size +-XX:MaxNewSize=size
         * 幸存区比例（动态）      -XX:InitialSurvivorRatio=ratio-XX:+UseAdaptiveSizePolicy
         * 幸存区比例             -XX:SurvivorRatio=ratio
         * 晋升阈值              -XX:MaxTenuringThreshold=threshold
         * 晋升详情                -XX:+PrintTenuringDistribution
         * GC详情                 -XX:+PrintGCDetails -verbose:gc
         * FullGC前MinorGC        -XX:+ScavengeBeforeFullGC
         */

    }
}
