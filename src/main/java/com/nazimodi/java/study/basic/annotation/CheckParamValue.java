package com.nazimodi.java.study.basic.annotation;

import java.lang.annotation.*;

/**
 * @author renqingwang on 2017/12/12.
 * @version 1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.TYPE})
public @interface CheckParamValue {
    String value() default "ricky";

    String name() default "nickname";

    int size() default 8;
}
