package com.cathetine.factory;

import com.cathetine.model.User;

/**
 * @author xjk
 * @date 2020/8/16 -  23:14
 **/
public abstract class AbstractUserFactory {

    public User get() {
        User user = getUser();
        user.setUserName("Cat");
        user.setPhone("1597575622");
        user.setSex(1);
        return user;
    }

    protected abstract User getUser();

}
