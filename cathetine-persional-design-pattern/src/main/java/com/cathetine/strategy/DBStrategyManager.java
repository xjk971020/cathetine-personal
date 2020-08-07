package com.cathetine.strategy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xjk
 * @date 2020/7/16 -  23:40
 * DB策略类单例工厂
 **/
public class DBStrategyManager {

    private DBStrategyManager() {}

    public static DBStrategyManager getInstance() {
        return SingletonHolder.dbStrategyManager;
    }

    private static class SingletonHolder {
        private static DBStrategyManager dbStrategyManager = new DBStrategyManager();
    }

    private static Map<String,Class> StrategyMap = new HashMap<>();

    static {
        for (DBEnum value : DBEnum.values()) {
            StrategyMap.put(value.getType(), value.getClazz());
        }
    }

    private static Class getClass(String type) {
        return StrategyMap.get(type);
    }

    public void execute(String type) {
        Class dbStrategy = getClass(type);
        try {
            Method execute = dbStrategy.getDeclaredMethod("execute");
            execute.invoke(dbStrategy.newInstance());
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

}
