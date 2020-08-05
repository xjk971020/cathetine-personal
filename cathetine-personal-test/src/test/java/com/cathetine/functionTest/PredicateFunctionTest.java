package com.cathetine.functionTest;

import com.cathetine.model.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:xjk
 * @Description:
 * @Date: 2020/8/5
 */
public class PredicateFunctionTest {
    @Test
    public void test() {

        List<User> users = new ArrayList<>();

        User user1 = new User();
        user1.setId(1L);
        user1.setPhone("1587655879");

        User user2 = new User();
        user2.setId(2L);
        user2.setPhone("1998220938");

        User user3 = new User();
        user3.setId(3L);
        user3.setPhone("110");

        users.add(user1);
        users.add(user2);
        users.add(user3);


        System.out.println(users.toString());

        users.removeIf(User::isPolice);

        System.out.println(users.toString());

        User user = new User();
        user.setAmt(10000.0);
        user.setSex(1);
        user.isRichMan(User::isRich, User::isMan);



    }
}
