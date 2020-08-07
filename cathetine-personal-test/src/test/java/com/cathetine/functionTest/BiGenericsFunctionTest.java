package com.cathetine.functionTest;

import com.cathetine.model.ConcreteBiGenerics;
import com.cathetine.model.User;
import org.junit.Test;

/**
 * @Author:xjk
 * @Description:
 * @Date: 2020/8/7
 */
public class BiGenericsFunctionTest {

    @Test
    public void test() {
        User user = new User();
        user.setUserName("Cathe");
        user.setSex(1);
        ConcreteBiGenerics<User, String> generics = ConcreteBiGenerics.getGenerics(user, User::desc);
        System.out.println(generics.toString());
    }
}
