package com.nazimodi.java.study.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * array 和 list性能比较
 *
 * @author renqingwang on 2017/8/13.
 * @version 1.0
 */
public class ArrayVsList {
    public static void main(String[] args) {
        int count = 0;
        for (int i = 0; i < 10000; i++) {
            if (arrayVsList()) {
                count++;
            }
        }
        System.out.print("比较次数：" + 10000 + "\n");
        System.out.print("数组效率大于列表次数：" + count + "\n");
        System.out.print("数组效率小于列表次数：" + (10000 - count) + "\n");
    }

    public static boolean arrayVsList() {
        char[] chars = new char[1000];
        List<Character> characterList = new ArrayList<Character>(chars.length);
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (65 + i % 65);
            characterList.add((char) (65 + i % 26));
        }
        long currentTime = System.nanoTime();
        new String(chars);
        System.gc();
        long arrayTime = System.nanoTime() - currentTime;
        currentTime = System.nanoTime();
        characterList.toString();
        long listTime = System.nanoTime() - currentTime;
        return arrayTime < listTime;
    }
}
