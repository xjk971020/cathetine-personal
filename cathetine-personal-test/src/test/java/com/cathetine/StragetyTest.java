package com.cathetine;

import com.cathetine.strategy.DBEnum;
import com.cathetine.strategy.DBStrategyManager;
import org.junit.Test;

/**
 * @author xjk
 * @date 2020/7/16 -  23:47
 **/
public class StragetyTest {

    @Test
    public void test() {
        DBStrategyManager dbStrategyManager = DBStrategyManager.getInstance();
        dbStrategyManager.execute(DBEnum.MYSQL_DB_ENUM.getType());
    }
}
