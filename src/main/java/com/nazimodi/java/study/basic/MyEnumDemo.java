package com.nazimodi.java.study.basic;

import com.nazimodi.java.study.basic.myenum.DirectionEnum;

/**
 * @author renqingwang on 2017/8/20.
 * @version 1.0
 */
public class MyEnumDemo {
    public static void main(String[] args) {
        DirectionEnum east = DirectionEnum.EAST;
        switch (east) {
            case EAST:
                System.out.println("东方");
                break;
            case SOURCE:
                System.out.println("南方");
                break;
            case WEST:
                System.out.println("西方");
                break;
            case NORTH:
                System.out.println("北方");
                break;
        }
    }
}
