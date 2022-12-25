package com.exji.jvm.memoryAndGc.test;

import java.util.ArrayList;
import java.util.List;

public class StringTableTest3 {
    /**
     * 在jdk8下设置 -Xmx10m -XX:-UseGCOverheadLimit
     * @param args
     */
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int i = 0;
        try {
            for (int j = 0; j < 260000; j++) {
                list.add(String.valueOf(j).intern());
                i++;
            }
        } catch (Throwable e) {
            /**
             * java.lang.OutOfMemoryError: Java heap space  常量池溢出
             */
            e.printStackTrace();
        } finally {
            System.out.println(i);
        }
    }
}
