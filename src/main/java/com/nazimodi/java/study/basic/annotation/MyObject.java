package com.nazimodi.java.study.basic.annotation;

/**
 * @author renqingwang on 2017/12/12.
 * @version 1.0
 */
@CheckParamValue
public class MyObject {
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
