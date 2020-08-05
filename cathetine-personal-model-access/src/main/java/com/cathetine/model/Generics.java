package com.cathetine.model;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @Author:xjk
 * @Description:
 * @Date: 2020/7/14
 */
public class Generics<T> {

    T t;

    List<T> list;

    public Generics(T t) {
        this.t = t;
    }

    public Generics(List<T> list) {
        this.list = list;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public static void printAll(List<?> list) {
        list.forEach(item -> System.out.println(item.getClass()));
        for (Object o : list) {
            System.out.println(o);
        }

    }

    public T get(Supplier<T> supplier) {
        Objects.requireNonNull(supplier);
        return supplier.get();
    }

    public void operate(Consumer<T> consumer) {
        Objects.requireNonNull(consumer);
        consumer.accept(this.t);
    }
}
