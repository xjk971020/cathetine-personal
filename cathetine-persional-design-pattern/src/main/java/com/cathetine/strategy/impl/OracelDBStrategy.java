package com.cathetine.strategy.impl;

import com.cathetine.strategy.DBBaseStrategy;

/**
 * @author xjk
 * @date 2020/7/16 -  23:24
 **/
public class OracelDBStrategy implements DBBaseStrategy {
    public void connect() {
        System.out.println("connect to oracle server..");
    }

    public void execute() {
        System.out.println("do oracle execute");
    }

    public void disconnect() {
        System.out.println("disconnect from oracle serer..");
    }
}
