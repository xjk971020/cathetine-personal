package com.cathetine.factory;

import com.cathetine.model.User;

/**
 * @author xjk
 * @date 2020/8/16 -  23:21
 **/
public class ChineseUserFactory extends AbstractUserFactory {
    @Override
    public User getUser() {
        User user = get();
        user.setNation("Chinese");
        return user;
    }
}
