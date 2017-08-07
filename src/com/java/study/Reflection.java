package com.java.study;

import java.lang.reflect.Array;
import java.lang.reflect.Method;

/**
 * @author renqingwang on 2017/8/7.
 * @version 1.0
 */
public class Reflection {
    public static void reflectionOne() {
        try {
            Class classType = Class.forName("com.java.study.QuickSort");
            Method[] methods = classType.getDeclaredMethods();
            try {
                try {
                    QuickSort quickSort = (QuickSort)classType.newInstance();
                    quickSort.justForTest();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                QuickSort.quickSort(new int[]{1,3,2}, 0, 2);
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
            System.out.print(new SecurityManager(){
                public String getClassName() {
                    return getClassContext()[1].getName();
                }
            }.getClassName() + "::" + new Throwable().getStackTrace()[0].getMethodName() + "\n");
            for (Method method : methods) {
                System.out.print(method + "\n");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void reflectionSecond() {
        Class classType = (new QuickSort()).getClass();
        Method[] methods = classType.getDeclaredMethods();
        System.out.print(new SecurityManager(){
            public String getClassName() {
                return getClassContext()[1].getName();
            }
        }.getClassName() + "::" + new Throwable().getStackTrace()[0].getMethodName() + "\n");
        for (Method method : methods) {
            System.out.print(method + "\n");
        }
    }

    public static void main(String[] args) {
        reflectionOne();
        reflectionSecond();
    }
}
