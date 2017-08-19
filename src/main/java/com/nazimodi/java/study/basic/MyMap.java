package com.nazimodi.java.study.basic;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author renqingwang on 2017/8/19.
 * @version 1.0
 */
public class MyMap {
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            hashMap.put("key" + i, "value" + i);
        }

        //获取键对
        System.out.println("键对：" + hashMap.keySet());
        System.out.println("值对：" + hashMap.values());
        System.out.println("键值对：" + hashMap.entrySet());

        Set<Map.Entry<String, String>> collection = hashMap.entrySet();
        for (Map.Entry<String, String> map : collection) {
            System.out.println(map.getKey() + ":" + map.getValue());
        }

        checkPerformance(hashMap);
    }

    public static void checkPerformance(HashMap<String, String> hashMap) {

        long currentTime = System.nanoTime();
        int length = 1000000;
        for (int i = 0; i < length; i++) {
            Set<Map.Entry<String, String>> collection = hashMap.entrySet();
            for (Map.Entry<String, String> map : collection) {
                //System.out.println(map.getKey() + ":" + map.getValue());
            }
        }
        System.out.println("先赋值后使用 消耗时间：" + (System.nanoTime() - currentTime) / 1000 / 1000 + "ms");


        for (int i = 0; i < length; i++) {
            for (Map.Entry<String, String> map : hashMap.entrySet()) {
                //System.out.println(map.getKey() + ":" + map.getValue());
            }
        }
        System.out.println("直接使用 消耗时间：" + (System.nanoTime() - currentTime) / 1000 / 1000 + "ms");
    }
}
