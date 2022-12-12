package com.exji.juc.forkJoin;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest {
    /**
     * fork/join
     */
    public static void main(String[] args) {
        ForkJoinPool p = new ForkJoinPool(4);
//        Integer invoke = p.invoke(new myTask(5));
//        System.out.println(invoke);

        Integer invoke = p.invoke(new MyTask2(1, 5));
        System.out.println(invoke);
    }
}

class myTask extends RecursiveTask<Integer>{
    private int n;

    public myTask(int n){
        this.n = n;
    }
    @Override
    protected Integer compute() {
        if(n == 1){
            return 1;
        }
        myTask myTask = new myTask(n - 1);
        myTask.fork(); // 让一个线程去执行任务
        Integer join = myTask.join();
        System.out.println("join 获取的值 "+join);
        return n + join;
    }
}

@Slf4j
class MyTask2 extends RecursiveTask<Integer>{

    private int start;
    private int end;

    public MyTask2(int start,int end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute(


    ) {
        if(start == end){
            return start;
        }
        if(end - start == 1){
            return start + end;
        }
        int mid = (start + end) / 2;

        MyTask2 t1 = new MyTask2(start, mid);
        log.info("t1 start {} end {}",start,mid);
        t1.fork();

        MyTask2 t2 = new MyTask2(mid+1, end);
        log.info("t2 start {} end {}",mid+1,end);
        t2.fork();

        Integer result1 = t1.join();
        Integer result2 = t2.join();
        log.info("result1 {} result2 {}",result1,result2);
        return result1 + result2 ;
    }
}