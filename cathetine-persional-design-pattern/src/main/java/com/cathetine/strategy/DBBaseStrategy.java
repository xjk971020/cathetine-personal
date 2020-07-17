package com.cathetine.strategy;

/**
 * @author xjk
 * @date 2020/7/16 -  23:18
 **/
public interface DBBaseStrategy {
    void connect();
    void execute();
    void disconnect();
}
