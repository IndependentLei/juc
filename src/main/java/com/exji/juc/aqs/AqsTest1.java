package com.exji.juc.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@Slf4j
public class AqsTest1 {
    public static void main(String[] args) {
        MyLock myLock = new MyLock();
        new Thread(()->{
            myLock.lock();
            try {
                log.info("t1 加锁成功");
                TimeUnit.SECONDS.sleep(50);
            }catch (Exception e){
                log.info("");
            } finally {
                myLock.unlock();
            }
        },"t1").start();

        new Thread(()->{
            myLock.lock();
            try {
                log.info("t2 加锁成功");
            } finally {
                myLock.unlock();
            }
        },"t2").start();
    }
}

// 自定义锁（不可重入锁）
class MyLock implements Lock{

    class Sync extends AbstractQueuedSynchronizer{
        @Override // 尝试获取锁
        protected boolean tryAcquire(int arg) {
            if(compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override // 尝试释放锁
        protected boolean tryRelease(int arg) {
            // 放在setState放在前面，没有用volatile修饰，在volatile之前有写屏障
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        @Override // 是否持有独占锁
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        public Condition newCondition(){
            return new ConditionObject();
        }
    }

    private Sync sync = new Sync();

    // 加锁（不成功会进入等待队列）
    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override // 加锁，可打断
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override // 尝试加锁（一次）
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override // 有超时时间的加锁
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override // 解锁
    public void unlock() {
        sync.release(0);
    }

    @Override // 条件变量
    public Condition newCondition() {
        return sync.newCondition();
    }
}
