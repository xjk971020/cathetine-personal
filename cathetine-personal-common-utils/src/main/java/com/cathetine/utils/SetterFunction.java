package com.cathetine.utils.type;

@FunctionalInterface
public interface SetterFunction<T, V> {

    void setValue(T bean, V value);
}
