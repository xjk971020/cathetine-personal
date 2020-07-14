package com.cathetine.utils;

@FunctionalInterface
public interface MatchFunction<T> {
    boolean isMatch(T bean);
}
