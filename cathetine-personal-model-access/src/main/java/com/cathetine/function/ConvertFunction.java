package com.cathetine.function;

/**
 * @Author:xjk
 * @Description:
 * @Date: 2020/8/7
 */
@FunctionalInterface
public interface ConvertFunction<T, R> {
    R convert(T t);
}
