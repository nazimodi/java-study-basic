package com.java.study;

import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author renqingwang on 2017/8/7.
 * @version 1.0
 */
public class Reflection {
    /**
     * 反射出对象的第一种方式 Class.forName
     */
    public static void reflectionOne() {
        try {
            Class classType = Class.forName("com.java.study.QuickSort");
            Method[] methods = classType.getDeclaredMethods();
            try {
                try {
                    QuickSort quickSort = (QuickSort) classType.newInstance();
                    quickSort.justForTest();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                QuickSort.quickSort(new int[]{1, 3, 2}, 0, 2);
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
            System.out.print(new SecurityManager() {
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

    /**
     * 通过对象的class属性
     */
    public static void reflectionSecond() {
        Class classType = (new QuickSort()).getClass();
        Method[] methods = classType.getDeclaredMethods();
        System.out.print(new SecurityManager() {
            public String getClassName() {
                return getClassContext()[1].getName();
            }
        }.getClassName() + "::" + new Throwable().getStackTrace()[0].getMethodName() + "\n");
        for (Method method : methods) {
            System.out.print(method + "\n");
        }
    }

    /**
     * 方法映射
     * getMethod (methodNameStr, ClassArrOfParam)
     * invoke(classObj, ObjectArrOfParams)
     */
    public static void reflectionThird() throws Exception {
        Class c = QuickSort.class;

        Object object = c.newInstance();
        Integer[] arr = {1, 8, 2, 4, 5};
        Class[] classes = new Class[3];
        classes[0] = int[].class;
        classes[1] = int.class;
        classes[2] = int.class;
        Object[] params = new Object[3];
        params[0] = new int[]{1, 8, 2, 4, 5};
        params[1] = 1;
        params[2] = arr.length - 1;

        System.out.print(new SecurityManager() {
            public String getClassName() {
                return getClassContext()[1].getName();
            }
        } + "::" + new Throwable().getStackTrace()[0].getMethodName());
        Method method = c.getMethod("quickSort", classes);

        method.invoke(object, params);

    }

    public static void main(String[] args) {
        reflectionOne();
        reflectionSecond();
        try {
            reflectionThird();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
