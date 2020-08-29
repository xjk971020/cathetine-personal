package com.cathetine.factory;

import com.cathetine.model.User;

/**
 * @author xjk
 * @date 2020/8/16 -  23:21
 **/
public class ChineseUserFactory extends AbstractUserFactory {
    @Override
    protected User getUser() {
        User user = new User();
        user.setNation("Chinese");
        return user;
    }
}
