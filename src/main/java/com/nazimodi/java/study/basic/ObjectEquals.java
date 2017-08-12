package com.nazimodi.java.study.basic;

import java.awt.*;

/**
 * @author renqingwang on 2017/8/12.
 * @version 1.0
 */
public class ObjectEquals {
    private String name;
    private int age;
    private double weight;
    private Color color;

    public static void main(String[] args) {
        ObjectEquals objectEquals1 = new ObjectEquals("张三", 20, 21.2, Color.BLUE);
        ObjectEquals objectEquals2 = new ObjectEquals("李晓", 20, 21.2, Color.YELLOW);
        ObjectEquals objectEquals3 = new ObjectEquals("张三", 20, 21.2, Color.BLUE);
        System.out.print(objectEquals1.toString() + "hash:" + objectEquals1.hashCode() + "\n\n");
        System.out.print(objectEquals2.toString() + "hash:" + objectEquals2.hashCode() + "\n\n");
        System.out.print(objectEquals3.toString() + "hash:" + objectEquals3.hashCode() + "\n\n");
        System.out.print("1 与 2相等：" + objectEquals1.equals(objectEquals2) + "\n\n");
        System.out.print("1 与 3相等：" + objectEquals1.equals(objectEquals3));
    }

    ObjectEquals(String name, int age, double weight, Color color) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (null == object) {
            return false;
        }
        if (getClass() != object.getClass()) {
            return false;
        }
        ObjectEquals objectEquals = (ObjectEquals) object;

        return this.name.equals(objectEquals.getName())
                && this.getAge() == objectEquals.getAge()
                && this.getWeight() == objectEquals.getWeight()
                && this.getColor() == objectEquals.getColor();

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = result * prime + age;
        result = result * prime + ((color == null) ? 0 : color.hashCode());
        result = result * prime + ((name == null) ? 0 : name.hashCode());
        long temp = Double.doubleToLongBits(weight);
        return result * prime + (int) (temp ^ (temp >>> 32));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("名字：" + name + "\n");
        stringBuilder.append("年龄：" + age + "\n");
        stringBuilder.append("体重：" + weight + "\n");
        stringBuilder.append("颜色：" + color.toString() + "\n");
        return stringBuilder.toString();
    }
}
