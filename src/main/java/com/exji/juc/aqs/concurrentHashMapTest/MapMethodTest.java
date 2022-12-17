package com.exji.juc.aqs.concurrentHashMapTest;

import java.util.HashMap;
import java.util.Map;

public class MapMethodTest {
    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<>();

        map.put("apple",1);
        map.put("pink",1);
        map.put("dog",1);
        map.put("cat",1);

        // 如果当前key存在，就什么操作也不进行，如果不存在，将key存入，第二个参数是存入value的操作（key）->{....}
        map.computeIfAbsent("apple1",(key)->3);

        // 如果存在，就进行计算，如果不存在，就忽略
        map.computeIfPresent("pink1",(key,val)->{
            System.out.println(val);
            return val;
        });

        // 不存在就添加进去，存在什么操作都不进行
        map.putIfAbsent("pink1",4);

        // 不关系存不存在，存在就更新value，不存在就put进去，返回值为新的value
        map.compute("11",(key,val)->1);

        // key不存在就直接存入value，存在就进行第三个参数的操作
        map.merge("11",2,(key,val)->3);

// {pink1=4, apple=1, pink=1, apple1=3, cat=1, dog=1}

        System.out.println(map);
    }
}
