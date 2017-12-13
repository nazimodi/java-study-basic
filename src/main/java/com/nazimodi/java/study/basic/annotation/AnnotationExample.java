package com.nazimodi.java.study.basic.annotation;

import java.util.HashMap;
import java.util.Map;

/**
 * @author renqingwang on 2017/12/12.
 * @version 1.0
 */
public class AnnotationExample {
    private static final Map<String, String> privilegeName = new HashMap<>();
    @CheckParamValue
    public static final String name = "name";

    @CheckParamValue
    public static void init(String name, String value) {

        privilegeName.put(name, value);
    }

    public static void main(String[] args) {

        try {
            CheckParamValue checkParamValue;
            //methods
            checkParamValue = AnnotationExample.class.getMethod("init", new Class[]{String.class, String.class}).getAnnotation(CheckParamValue.class);
            System.out.println(checkParamValue.name() + ":" + checkParamValue.value());

            //fields
            checkParamValue = AnnotationExample.class.getField("name").getAnnotation(CheckParamValue.class);
            System.out.println("before init:" + checkParamValue.name() + ":" + checkParamValue.value());
            //check type
            MyObject myObject = new MyObject();
            myObject.setName(name);
            myObject.setValue("hhh");
            checkParamValue = MyObject.class.getAnnotation(CheckParamValue.class);
            System.out.println(checkParamValue.name() + ":" + checkParamValue.value());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
