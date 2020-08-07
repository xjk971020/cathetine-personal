package com.cathetine.model;

import com.cathetine.function.ConvertFunction;

/**
 * @Author:xjk
 * @Description:
 * @Date: 2020/8/7
 */
public class ConcreteBiGenerics<T, R> extends AbstractBiGenerics<T, R> {

    public static<T, R> ConcreteBiGenerics<T, R> getGenerics(T t, ConvertFunction<T, R> convertFunction) {
        ConcreteBiGenerics<T, R> generics = new ConcreteBiGenerics();
        generics.setT(t);
        generics.setR(convert(t, convertFunction));
        return generics;
    }

    @Override
    public String toString() {
        return "ConcreteBiGenerics{" +
                "t=" + t +
                ", r=" + r +
                '}';
    }
}
