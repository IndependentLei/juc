package com.exji.jvm.memoryAndGc.test1;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class DirectBufferTest {
    private static final String FROM = "E:\\百度网盘download\\RabbitMQ.rar";
    private static final String TO = "E:\\百度网盘download\\rabbitMq1.rar";
    private static final Integer _1MB = 1024 * 1024;
    public static void main(String[] args) {
        // 直接内存
        io();
        directBuffer();


        /**
         * io speed : 874.3881
         * directBuffer speed : 92.2689
         */
        /**
         * Direct Memory  直接内存
         * ·常见于NIO操作时，用于数据缓冲区
         * ·分配回收成本较高，但读写性能高
         * ·不受JVM内存回收管理
         */

        /**
         * ·使用了Unsafe对象完成直接内存的分配回收，并且回收需要主动调用freeMemory方法
         * ·ByteBuffer的实现类内部，使用了Cleaner(虚引用)来监测ByteBuffer对象，一旦ByteBuffer对象被垃
         * 圾回收，那么就会由ReferenceHandler线程通过Cleaner的clean方法调用freeMemory来释放直接内存
         */
    }

    private static void directBuffer(){
        long start = System.nanoTime();
        try(
                FileChannel fromChannel = new FileInputStream(FROM).getChannel();
                FileChannel toChannel = new FileOutputStream(TO).getChannel();
        ) {
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(_1MB);
            while (true){
                int len = fromChannel.read(byteBuffer);
                if( len == -1 ){
                    break;
                }
                byteBuffer.flip();
                toChannel.write(byteBuffer);
                byteBuffer.clear();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        long end = System.nanoTime();
        System.out.println("directBuffer speed : "+(end-start)/1000_000.0);
    }

    private static void io(){
        long start = System.nanoTime();
        try(
                InputStream is = new FileInputStream(FROM);
                OutputStream os = new FileOutputStream(TO);
        ) {
            byte[] byteBuffer = new byte[1024];
            while (true){

                int len = is.read(byteBuffer);
                if( len == -1 ){
                    break;
                }
                os.write(byteBuffer);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        long end = System.nanoTime();
        System.out.println("IO speed : "+(end-start)/1000_000.0);
    }

}
