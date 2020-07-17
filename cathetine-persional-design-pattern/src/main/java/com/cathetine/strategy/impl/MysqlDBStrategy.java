package com.cathetine.strategy.impl;

import com.cathetine.strategy.DBBaseStrategy;

/**
 * @author xjk
 * @date 2020/7/16 -  23:23
 **/
public class MysqlDBStrategy implements DBBaseStrategy {
    public void connect() {
        System.out.println("connect to mysql server..");
    }

    public void execute() {
        System.out.println("do mysql execute");
    }

    public void disconnect() {
        System.out.println("disconnect from mysql serer..");
    }
}
