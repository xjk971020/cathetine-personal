package com.cathetine.strategy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xjk
 * @date 2020/7/16 -  23:40
 **/
public class DBStrategyManager {

    private DBStrategyManager() {}

    public static DBStrategyManager getInstance() {
        return SingletonHolder.dbStrategyManager;
    }

    private static class SingletonHolder {
        private static DBStrategyManager dbStrategyManager = new DBStrategyManager();
    }

    private static Map<String,Class> stragetyMap = new HashMap<>();

    static {
        for (DBEnum value : DBEnum.values()) {
            stragetyMap.put(value.getType(), value.getClazz());
        }
    }

    private Class getClass(String type) {
        return stragetyMap.get(type);
    }

    public void execute(String type) {
        Class dbStragety = getClass(type);
        try {
            Method execute = dbStragety.getDeclaredMethod("execute");
            execute.invoke(dbStragety.newInstance());
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

}
