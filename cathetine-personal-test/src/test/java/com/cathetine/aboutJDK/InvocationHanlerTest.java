package com.cathetine.aboutJDK;

import com.cathetine.factory.AbstractUserFactory;
import com.cathetine.factory.ChineseUserFactory;
import com.cathetine.model.User;
import com.cathetine.proxy.DBHandler;
import com.cathetine.proxy.UserHandler;
import com.cathetine.strategy.DBBaseStrategy;
import com.cathetine.strategy.DBStrategyManager;
import com.cathetine.strategy.impl.MysqlDBStrategy;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author xjk
 * @date 2020/8/16 -  23:08
 **/
public class InvocationHanlerTest {

    @Test
    public void test() {
        //要代理的真实对象
        DBBaseStrategy mysqlStragety = new MysqlDBStrategy();
        DBHandler<DBBaseStrategy> mysqlDbHandler = new DBHandler<>(mysqlStragety);

        DBBaseStrategy dbBaseStrategy = mysqlDbHandler.get();
        dbBaseStrategy.execute();

    }
}
