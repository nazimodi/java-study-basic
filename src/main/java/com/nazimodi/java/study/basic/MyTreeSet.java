package com.nazimodi.java.study.basic;

import java.util.*;

/**
 * @author renqingwang on 2017/8/19.
 * @version 1.0
 */
public class MyTreeSet {
    public static void main(String[] args) {
        TreeSet<Integer> integerTreeSet = new TreeSet<Integer>();
        Collections.addAll(integerTreeSet, 1, 3, 2, 5, 4, 6);
        System.out.println("tree set:" + integerTreeSet);
        System.out.println("tree set reverse:" + integerTreeSet.descendingSet());
        Integer higher = integerTreeSet.higher(4);
        Integer lower = integerTreeSet.lower(3);
        Iterator<Integer> integerIterator = integerTreeSet.iterator();
        while (integerIterator.hasNext()) {
            System.out.print(integerIterator.next() + "");
        }
        System.out.println();
        integerIterator = integerTreeSet.descendingIterator();
        while (integerIterator.hasNext()) {
            System.out.print(integerIterator.next() + "");
        }
        System.out.println();
        System.out.println("higher than 4:" + higher);
        System.out.println("lower than 3:" + lower);
        System.out.println("the first element:" + integerTreeSet.pollFirst() + " the last element:" + integerTreeSet.pollLast());
        System.out.println("elements middle in [3, 5]:" + integerTreeSet.subSet(3, true, 5, true));
        System.out.println("current set:" + integerTreeSet);
    }
}