package com.exji.jvm.memoryAndGc.test1;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ReferenceTest {
    // 软引用
    /**
     * -Xmx20m -XX:+PrintGCDetails -verbose:gc
     */
    private static final Integer SIZE = 1024*1024*4;
    public static void main(String[] args) throws IOException {
        // 强引用
//        List<byte[]> list = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            list.add(new byte[SIZE]);
//        }
//        System.in.read();
        // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
//        soft();
//        softQueue();
        weakReference();
    }

    private static void soft() throws IOException {
        // 弱引用
        List<SoftReference<byte[]>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SoftReference<byte[]> softReference = new SoftReference<>(new byte[SIZE]);
            System.out.println(softReference.get());
            list.add(softReference);
        }
        System.in.read();
        System.out.println("------>");
        for (int i = 0; i < 5; i++) {
            System.out.println(list.get(i).get());
        }
    }

    private static void softQueue(){
        // 使用软引用队列
        List<SoftReference<byte[]>> list = new ArrayList<>();

        // 引用队列
        ReferenceQueue<byte[]> queue = new ReferenceQueue<>();
        for (int i = 0; i < 5; i++) {
            SoftReference<byte[]> softReference = new SoftReference<>(new byte[SIZE],queue);
            System.out.println(softReference.get());
            list.add(softReference);
        }

        Reference<? extends byte[]> poll = queue.poll();
        while (poll != null){
            list.remove(poll);
            poll = queue.poll();
        }

        System.out.println("=============>");
        for (SoftReference<byte[]> softReference : list) {
            System.out.println(softReference.get());
        }
        /**
         * [B@3b192d32
         * [B@16f65612
         * [B@311d617d
         * [GC (Allocation Failure) [PSYoungGen: 2285K->504K(6144K)] 14573K->13052K(19968K), 0.0012095 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
         * [B@7c53a9eb
         * [GC (Allocation Failure) --[PSYoungGen: 4712K->4712K(6144K)] 17260K->17276K(19968K), 0.0032410 secs] [Times: user=0.06 sys=0.00, real=0.00 secs]
         * [Full GC (Ergonomics) [PSYoungGen: 4712K->4547K(6144K)] [ParOldGen: 12564K->12528K(13824K)] 17276K->17076K(19968K), [Metaspace: 3038K->3038K(1056768K)], 0.0055399 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
         * [GC (Allocation Failure) --[PSYoungGen: 4547K->4547K(6144K)] 17076K->17076K(19968K), 0.0017045 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
         * [Full GC (Allocation Failure) [PSYoungGen: 4547K->0K(6144K)] [ParOldGen: 12528K->677K(8704K)] 17076K->677K(14848K), [Metaspace: 3038K->3038K(1056768K)], 0.0069241 secs] [Times: user=0.09 sys=0.00, real=0.01 secs]
         * [B@ed17bee
         * =============>
         * [B@ed17bee
         * Heap
         *  PSYoungGen      total 6144K, used 4264K [0x00000000ff980000, 0x0000000100000000, 0x0000000100000000)
         *   eden space 5632K, 75% used [0x00000000ff980000,0x00000000ffdaa370,0x00000000fff00000)
         *   from space 512K, 0% used [0x00000000fff00000,0x00000000fff00000,0x00000000fff80000)
         *   to   space 512K, 0% used [0x00000000fff80000,0x00000000fff80000,0x0000000100000000)
         *  ParOldGen       total 8704K, used 677K [0x00000000fec00000, 0x00000000ff480000, 0x00000000ff980000)
         *   object space 8704K, 7% used [0x00000000fec00000,0x00000000feca9518,0x00000000ff480000)
         *  Metaspace       used 3045K, capacity 4486K, committed 4864K, reserved 1056768K
         *   class space    used 322K, capacity 386K, committed 512K, reserved 1048576K
         * Disconnected from the target VM, address: '127.0.0.1:64819', transport: 'socket'
         *
         * Process finished with exit code 0
         */
    }

    /**
     * 弱引用
     */
    private static void weakReference(){
        List<WeakReference<byte[]>> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            WeakReference<byte[]> weakReference = new WeakReference<>(new byte[SIZE]);
            System.out.println(weakReference.get());
            list.add(weakReference);
        }

        System.out.println("=============>");
        for (WeakReference<byte[]> weakReference : list) {
            System.out.println(weakReference.get());
        }
        /**
         * [B@3b192d32
         * [B@16f65612
         * [B@311d617d
         * [GC (Allocation Failure) [PSYoungGen: 2291K->488K(6144K)] 14579K->13080K(19968K), 0.0025767 secs] [Times: user=0.00 sys=0.00, real=0.02 secs]
         * [B@7c53a9eb
         * [GC (Allocation Failure) [PSYoungGen: 4696K->504K(6144K)] 17288K->13120K(19968K), 0.0007950 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
         * [B@ed17bee
         * =============>
         * [B@3b192d32
         * [B@16f65612
         * [B@311d617d
         * null
         * [B@ed17bee
         * Heap
         *  PSYoungGen      total 6144K, used 4768K [0x00000000ff980000, 0x0000000100000000, 0x0000000100000000)
         *   eden space 5632K, 75% used [0x00000000ff980000,0x00000000ffdaa2f0,0x00000000fff00000)
         *   from space 512K, 98% used [0x00000000fff80000,0x00000000ffffe010,0x0000000100000000)
         *   to   space 512K, 0% used [0x00000000fff00000,0x00000000fff00000,0x00000000fff80000)
         *  ParOldGen       total 13824K, used 12616K [0x00000000fec00000, 0x00000000ff980000, 0x00000000ff980000)
         *   object space 13824K, 91% used [0x00000000fec00000,0x00000000ff852030,0x00000000ff980000)
         *  Metaspace       used 3045K, capacity 4486K, committed 4864K, reserved 1056768K
         *   class space    used 322K, capacity 386K, committed 512K, reserved 1048576K
         */
    }
}
