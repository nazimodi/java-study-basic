package com.nazimodi.java.study.basic.jvm;

/**
 * -verbose:gc -Xms20m -Xmx20m -Xmn10m
 * -XX:+UseSerialGC -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+PrintTenuringDistribution
 * @author renqingwang on 2017/11/6.
 * @version 1.0
 */
public class MinorGC {
    private static final int _1MB = 1024 * 1024;
    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[_1MB / 4];
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];
        allocation3 = null;
        allocation3 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];
    }
}
