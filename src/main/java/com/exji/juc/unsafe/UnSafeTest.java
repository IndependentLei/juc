package com.exji.juc.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnSafeTest {
    public static void main(String[] args) {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            Unsafe unsafe =(Unsafe) theUnsafe.get(null);
            // 计算偏移量
            long id = unsafe.objectFieldOffset(Student.class.getDeclaredField("id"));
            long name = unsafe.objectFieldOffset(Student.class.getDeclaredField("name"));
            Student student = new Student();
            // cas操作
            // 传入 student
            boolean b = unsafe.compareAndSwapInt(student, id, student.id, 1);
            boolean c = unsafe.compareAndSwapObject(student, name, student.name, "校长");

            System.out.println(student.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class Student{
    volatile int id;
    volatile String name;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
