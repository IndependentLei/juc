package com.exji.jvm.classL.test;

public class LazyHolderTest {
    public static void main(String[] args) {
        Singleton.getInstance();
    }
}

// 懒加载
class Singleton{
    private Singleton(){

    }

    private static class LazyHolder{
        private static final Singleton SINGLETON = new Singleton();

        static {
            System.out.println("LazyHolder init");
        }
    }

    public static void test(){
        System.out.println("test");
    }

    public static Singleton getInstance(){
        return LazyHolder.SINGLETON;
    }
}


