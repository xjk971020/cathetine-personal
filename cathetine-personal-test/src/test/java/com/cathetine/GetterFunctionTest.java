package com.cathetine;

import com.cathetine.model.User;
import com.cathetine.utils.BeanUtils;
import com.cathetine.utils.GetterFunction;
import org.junit.Test;

/**
 * @Author:xjk
 * @Description:GetterFunction类测试
 * @Date: 2020/7/14
 */
public class GetterFunctionTest {

    @Test
    public void testGetterFunction() {
        User user1 = new User();
        User user2 = new User();
        /** 原始形式 */
        BeanUtils.fieldEquals(user1, user2, new GetterFunction<User, Object>() {
            @Override
            public Object getValue(User o) {
                return o.getUserName();
            }
        });

        /** lamaba */
        BeanUtils.fieldEquals(user1, user2, user -> user.getUserName());

        /** 方法引用 */
        boolean equals = BeanUtils.fieldEquals(user1, user2, User::getUserName);

        System.out.println(equals);
    }

}
