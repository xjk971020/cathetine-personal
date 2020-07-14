package com.cathetine.utils;

@FunctionalInterface
public interface GetterFunction<T, V> {
    V getValue(T o);
}
