package com.nazimodi.java.study.basic.myenum;

/**
 * @author renqingwang on 2017/8/20.
 * @version 1.0
 */
public enum DirectionEnum{
    EAST(1, "东方"),
    SOURCE(2, "南方"),
    WEST(3, "西方"),
    NORTH(4, "北方");
    private Integer value;
    private String desc;
    private DirectionEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
