package com.cathetine.utils;

/**
 * @author xjk
 * @date 2020/8/7 -  22:11
 **/
public class TimeInterval {
    public long start;

    public TimeInterval() {
        this.start = System.currentTimeMillis();
    }

    public String getTimeInterval() {
        long end = System.currentTimeMillis();
        long interval = end - this.start;
        return "方法耗时:" + interval + "ms";
    }
}
