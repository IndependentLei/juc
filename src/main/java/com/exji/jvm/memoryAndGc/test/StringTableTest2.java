package com.exji.jvm.memoryAndGc.test;

public class StringTableTest2 {
    // StringTable ["a","b","ab"] hashtable 结构 不能扩容
    public static void main(String[] args) {
        /**
         * 常量池中的信息，都会被加载到运行时常量池中，这时 a b ab 都是常量池中的符号，还没有变为 java 字符串对象
         * ldc #2 会把 a 符号变为 "a" 字符串对象
         * ldc #3 会把 b 符号变为 "b" 字符串对象
         * ldc #4 会把 ab 符号变为 "ab" 字符串对象
         */
        String s1 = "a"; // 懒惰的行为
        String s2 = "b";
        String s3 = "ab";
        String s4 = s1 + s2;  // 相当于创建了一个新的对象 StringBuilder ,相当于 new StringBuilder().append("a").append("b").toString();

        System.out.println( s3 == s4);

        String s5 = "a" + "b"; // javac 在编译期间的优化，结果已经在编译期间确定为 ab

        System.out.println(s3 == s5);

        String s6 = "a" + s2;

        System.out.println(s3 == s6);
        /**
         ·常量池中的字符串仅是符号，第一次用到时才变为对象
         ·利用串池的机制，来避免重复创建字符串对象
         ·字符串变量拼接的原理是StringBuilder(l.8)
         ·字符串常量拼接的原理是编译期优化
         ·可以使用intern方法，主动将串池中还没有的字符串对象放入串池
         ·1.8将这个字符串对象尝试放入串池，如果有则并不会放入，如果没有则放入串池，会把串池中的对象
         返回
         ·1.6将这个字符串对象尝试放入串池，如果有则并不会放入，如果没有会把此对象复制一份，放入串
         池，会把串池中的对象返回
         */

        System.out.println(s5.intern() == s3);
        // 将这个字符串阿象尝试放入串池，如果有则并不会放入，如果没有则放入串池，会把串池中的对象返回
    }
}
