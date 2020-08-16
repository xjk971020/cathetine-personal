package com.cathetine.springboot.test_20200815.service;

import com.cathetine.model.User;
import com.cathetine.springboot.test_20200815.stragety.ILogStragetyService;

/**
 * @author xjk
 * @date 2020/8/15 -  16:03
 * 添加用户抽象类
 **/
public abstract class AbstractUserService {

    protected ILogStragetyService logStragetyService;

    public final void addUser() {
        System.out.println("添加用户");
        log();
    }

    protected abstract void log();
}
