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
        list.add(new byte[1024*1024*7]);
        list.add(new byte[1024*512]);
        list.add(new byte[1024*512]);
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
    }
}
