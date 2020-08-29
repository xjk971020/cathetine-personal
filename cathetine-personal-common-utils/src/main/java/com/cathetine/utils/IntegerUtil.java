package com.cathetine.utils;

/**
 * @author xjk
 * @date 2020/8/29 -  15:51
 **/
public class IntegerUtil {
    public static boolean isEquals(Integer arg1, Integer arg2){
        if (arg1 == null || arg2 == null) {
            return false;
        }
        return arg1.equals(arg2);
    }

    public static boolean isNotEquals(Integer arg1, Integer arg2) {
        return !isEquals(arg1, arg2);
    }
}
