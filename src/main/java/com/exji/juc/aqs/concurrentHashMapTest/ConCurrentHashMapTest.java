package com.exji.juc.aqs.concurrentHashMapTest;

import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ConCurrentHashMapTest {
    private static final String str = "abcdefghijklmnopqrstuvwsyz";
    public static void main(String[] args) {
//        demo2();
//        demo3();
        demo1(()->new ConcurrentHashMap<String, LongAdder>(),(map, str)->{

            // 这种线程是不安全的，get 和 put 方法结合起来是不安全的
//            Long aLong = map.get(str);
//            Long cout = aLong == null ? 1 : aLong+1;
//            map.put(str,cout);

            // 为缺少当前key的情况下提供value，已经存在的key不进行操作（没有影响）
            LongAdder longAdder = map.computeIfAbsent(str, (adder) -> new LongAdder());
            longAdder.increment();

        });
    }


    public static <T> void demo1(Supplier<T> supplier, BiConsumer<T,String> consumer){
        T t = supplier.get();
        for (int i = 0; i < 26; i++) {
            int k = i+1;
            new Thread(()->{
                try {
                    File file = new File("D:\\workspace\\UnderCode\\juc\\src\\main\\resources\\templates\\file【"+k+"】.txt");
                    FileInputStream fis = new FileInputStream(file);
                    byte[] bytes = new byte[1];
                    int read;
                    while ((read = fis.read(bytes)) != -1){
                        String newStr = new String(bytes, 0, bytes.length);
                        if(!newStr.equals("\n")){
                            consumer.accept(t,newStr);
                        }
                    }
                    fis.close();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }).start();
        }

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(t.toString());
    }

    public static void demo2(){
        for (int i = 0; i < 26; i++) {
            int k = i+1;
            new Thread(()->{
                try {
                    File file = new File("D:\\workspace\\UnderCode\\juc\\src\\main\\resources\\templates\\file【"+k+"】.txt");
                    System.out.println(file.createNewFile());
                    FileOutputStream fos = new FileOutputStream(file);
                    for (int i1 = 0; i1 < str.length(); i1++) {
                        fos.write(str.charAt(i1));
                        fos.write("\n".getBytes(StandardCharsets.UTF_8));
                    }
                    fos.close();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }).start();
        }
    }

    public static void demo3(){
        try{
            File file = new File("D:\\myfile.txt");
            if(file.createNewFile())
                System.out.println("文件创建成功！");
            else
                System.out.println("出错了，该文件已经存在。");
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
