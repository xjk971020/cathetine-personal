package com.cathetine.functionTest;

import com.cathetine.model.User;
import org.junit.Test;

import java.util.function.DoubleFunction;

/**
 * @Author:xjk
 * @Description:
 * @Date: 2020/8/5
 */
public class DoubleFunctionTest {

    @Test
    public void test() {
        User user = new User();
        user.setAmt(10000.0);
        user.setAmtFunction(new DoubleFunction<User>() {
            @Override
            public User apply(double value) {
                return this.apply(value);
            }
        });

    }
}
