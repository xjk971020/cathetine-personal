package com.cathetine.model;

import java.util.List;
import java.util.function.Consumer;

/**
 * @Author:xjk
 * @Description:
 * @Date: 2020/7/14
 */
public class Generics<T> {
    List<T> data;

    public Generics(List<T> data) {
        this.data = data;
    }

}
