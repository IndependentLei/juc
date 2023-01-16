package com.exji.jvm.memoryAndGc.test1.分代回收;

import java.util.ArrayList;
import java.util.List;

public class GarbageCollectionTest2 {

    // -Xms20m -Xmx20m -Xmn10m -XX:+UseSerialGC -XX:+PrintGCDetails -verbose:gc
    public static void main(String[] args) {
        /**
         * Heap  堆空间  (没有任何对象的gc的使用情况)
         *
         *  新生代
         *  def new generation   total 9216K, used 2515K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
         *
         *   伊甸园空间 8M
         *   eden space 8192K,  30% used [0x00000000fec00000, 0x00000000fee74c30, 0x00000000ff400000)
         *
         *   from空间 1M
         *   from space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
         *
         *   to空间 1M
         *   to   space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
         *
         *
         *   老年代
         *  tenured generation   total 10240K, used 0K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
         *    the space 10240K,   0% used [0x00000000ff600000, 0x00000000ff600000, 0x00000000ff600200, 0x0000000100000000)
         *  元空间
         *  Metaspace       used 3040K, capacity 4486K, committed 4864K, reserved 1056768K
         *   class space    used 322K, capacity 386K, committed 512K, reserved 1048576K
         */

        List<byte[]> list = new ArrayList<>();
//        list.add(new byte[1024*1024*7]);
//        list.add(new byte[1024*512]);
//        list.add(new byte[1024*512]);
        /**
         * 触发一次垃圾回收
         * [GC (Allocation Failure) [DefNew: 2350K->693K(9216K), 0.0894797 secs] 2350K->693K(19456K), 0.0895537 secs] [Times: user=0.00 sys=0.00, real=0.09 secs]
         * Heap
         *  def new generation   total 9216K, used 8107K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
         *   eden space 8192K,  90% used [0x00000000fec00000, 0x00000000ff33d8c0, 0x00000000ff400000)
         *   from space 1024K,  67% used [0x00000000ff500000, 0x00000000ff5ad508, 0x00000000ff600000)
         *   to   space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
         *  tenured generation   total 10240K, used 0K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
         *    the space 10240K,   0% used [0x00000000ff600000, 0x00000000ff600000, 0x00000000ff600200, 0x0000000100000000)
         *  Metaspace       used 3040K, capacity 4486K, committed 4864K, reserved 1056768K
         *   class space    used 322K, capacity 386K, committed 512K, reserved 1048576K
         */

        /**
         * 触发两次垃圾回收
         * [GC (Allocation Failure) [DefNew: 2350K->693K(9216K), 0.0018188 secs] 2350K->693K(19456K), 0.0018972 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
         * [GC (Allocation Failure) [DefNew: 8537K->512K(9216K), 0.0077370 secs] 8537K->8370K(19456K), 0.0077661 secs] [Times: user=0.00 sys=0.02, real=0.01 secs]
         * Heap
         *  def new generation   total 9216K, used 1106K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
         *   eden space 8192K,   7% used [0x00000000fec00000, 0x00000000fec94930, 0x00000000ff400000)
         *   from space 1024K,  50% used [0x00000000ff400000, 0x00000000ff480048, 0x00000000ff500000)
         *   to   space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
         *  tenured generation   total 10240K, used 7858K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
         *    the space 10240K,  76% used [0x00000000ff600000, 0x00000000ffdac8c0, 0x00000000ffdaca00, 0x0000000100000000)
         *  Metaspace       used 3040K, capacity 4486K, committed 4864K, reserved 1056768K
         *   class space    used 322K, capacity 386K, committed 512K, reserved 1048576K
         */

        // 大对象直接晋升到老年代
        list.add(new byte[8 * 1024 * 1024]);
        list.add(new byte[8 * 1024 * 1024]);// 第二次就会内存溢出，会触发两次gc
        /**
         * 不会触发 gc
         * Heap
         *  def new generation   total 9216K, used 2515K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
         *   eden space 8192K,  30% used [0x00000000fec00000, 0x00000000fee74c30, 0x00000000ff400000)
         *   from space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
         *   to   space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
         *  tenured generation   total 10240K, used 8192K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
         *    the space 10240K,  80% used [0x00000000ff600000, 0x00000000ffe00010, 0x00000000ffe00200, 0x0000000100000000)
         *  Metaspace       used 3039K, capacity 4486K, committed 4864K, reserved 1056768K
         *   class space    used 322K, capacity 386K, committed 512K, reserved 1048576K
         */

        /**
         * 第二次就会内存溢出，会触发两次gc
         * [GC (Allocation Failure) [DefNew: 2350K->693K(9216K), 0.0103581 secs][Tenured: 8192K->8884K(10240K), 0.0190839 secs] 10542K->8884K(19456K), [Metaspace: 3032K->3032K(1056768K)], 0.0409839 secs] [Times: user=0.02 sys=0.00, real=0.04 secs]
         * [Full GC (Allocation Failure) [Tenured: 8884K->8869K(10240K), 0.0023995 secs] 8884K->8869K(19456K), [Metaspace: 3032K->3032K(1056768K)], 0.0024225 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
         * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
         * 	at com.exji.jvm.memoryAndGc.test1.分代回收.GarbageCollectionTest2.main(GarbageCollectionTest2.java:69)
         * Heap
         * Disconnected from the target VM, address: '127.0.0.1:56574', transport: 'socket'
         *  def new generation   total 9216K, used 246K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
         *   eden space 8192K,   3% used [0x00000000fec00000, 0x00000000fec3d890, 0x00000000ff400000)
         *   from space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
         *   to   space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
         *  tenured generation   total 10240K, used 8869K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
         *    the space 10240K,  86% used [0x00000000ff600000, 0x00000000ffea9448, 0x00000000ffea9600, 0x0000000100000000)
         *  Metaspace       used 3050K, capacity 4486K, committed 4864K, reserved 1056768K
         *   class space    used 324K, capacity 386K, committed 512K, reserved 1048576K
         */
    }
}
