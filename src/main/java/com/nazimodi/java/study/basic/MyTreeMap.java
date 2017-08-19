package com.nazimodi.java.study.basic;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * @author renqingwang on 2017/8/19.
 * @version 1.0
 */
public class MyTreeMap {
    public static class Person implements Comparable<Person>{
        String name;
        Integer age;

        Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "name:" + name + "-age:" + age;
        }

       public int compareTo(Person person){
            return this.name.compareTo(person.name);
       }
    }

    public static void main(String[] args) {
        TreeMap<Person, Integer> treeMap = new TreeMap<>();
        treeMap.put(new Person("zhang", 20), 1);
        treeMap.put(new Person("li", 20), 5);
        treeMap.put(new Person("wang", 33), 3);
        treeMap.put(new Person("zhao", 24), 4);
        treeMap.put(new Person("zhang", 24), 6);
        System.out.println("默认集合顺序为：" + treeMap.entrySet());

        treeMap = new TreeMap<Person, Integer>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.age - o2.age;
            }
        });
        treeMap.put(new Person("zhang", 20), 1);
        treeMap.put(new Person("li", 20), 5);
        treeMap.put(new Person("wang", 33), 3);
        treeMap.put(new Person("zhao", 24), 4);
        treeMap.put(new Person("zhu", 24), 6);
        System.out.println("排序后："+ treeMap.entrySet());
    }
}
