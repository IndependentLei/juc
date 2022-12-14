package com.exji.juc.aqs.readWriteLock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReetrantReadWriteLockTest {
    public static void main(String[] args) {
        // 读写锁
        DataContainer dataContainer = new DataContainer();
        new Thread(()->{
                dataContainer.write(1);

        },"t1").start();


        new Thread(()->{
            dataContainer.write(1);
        },"t1").start();
    }
}

@Slf4j
class DataContainer{
    private Object data;

    // 读 读 支持并发，读 写 不支持并发，写 写 也不支持并发
    private ReentrantReadWriteLock rw = new ReentrantReadWriteLock();

    private ReentrantReadWriteLock.ReadLock readLock = rw.readLock();

    private ReentrantReadWriteLock.WriteLock writeLock = rw.writeLock();


    public Object read(){
        log.info("获取读锁");
        readLock.lock();
        try {
            log.info("读取操作");
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            return data;
        } finally {
            log.info("释放读锁");
            readLock.unlock();
        }
    }

    public void write(Object obj){
        log.info("获取写锁");
        writeLock.lock();

        try {
            log.info("写入操作");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.data = obj;
        } finally {
            log.info("释放写锁");
            writeLock.unlock();
        }
    }
}
