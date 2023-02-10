package com.exji.jvm.classL.test;

public class ClassLoaderTest {
    public static void main(String[] args)  {
        // com/exji/jvm/classL/test/H.java
        Class<?> aClass = null;
        try {
            aClass = ClassLoaderTest.class.getClassLoader().loadClass("com.exji.jvm.classL.test.H");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
        }
        System.out.println(aClass);
    }
}
