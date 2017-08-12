package com.nazimodi.java.study.basic.dynamic.proxy;

import java.io.Serializable;

/**
 * @author renqingwang on 2017/8/12.
 * @version 1.0
 */
public class CountServiceImpl  implements CountService,Serializable {
    private int count = 0;

    @Override
    public int count() {
        return ++count;
    }
}
