package com.cathetine.strategy;

import com.cathetine.strategy.impl.MysqlDBStrategy;
import com.cathetine.strategy.impl.OracelDBStrategy;

/**
 * @author xjk
 * @date 2020/7/16 -  23:19
 **/
public enum  DBEnum {
    MYSQL_DB_ENUM("mysql", MysqlDBStrategy.class),
    ORACLE_DB_ENUM("oracle", OracelDBStrategy.class);

    private String type;
    private Class clazz;

    private DBEnum(String type, Class clazz) {
        this.type = type;
        this.clazz = clazz;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
}
