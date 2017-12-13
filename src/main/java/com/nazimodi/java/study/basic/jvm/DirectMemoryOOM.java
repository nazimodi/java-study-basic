package com.nazimodi.java.study.basic.jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * VM args: -Xmx10m -XX:MaxDirectMemorySize=5m
 *
 * @author renqingwang on 2017/11/1.
 * @version 1.0
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        boolean first = true;
        while (true) {
            if (first) {
                first = false;
                Thread.sleep(10000);
            }
            unsafe.allocateMemory(_1MB);
        }
    }
}
