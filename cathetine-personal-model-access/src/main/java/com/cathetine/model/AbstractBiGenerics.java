package com.cathetine.model;

import com.cathetine.function.ConvertFunction;

/**
 * @Author:xjk
 * @Description:
 * @Date: 2020/8/7
 */
public abstract class AbstractBiGenerics<T, R> {
    protected T t;
    protected R r;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public R getR() {
        return r;
    }

    public void setR(R r) {
        this.r = r;
    }

    public static<T, R> R convert(T t, ConvertFunction<T, R> convertFunction) {
        return convertFunction.convert(t);
    }


}
