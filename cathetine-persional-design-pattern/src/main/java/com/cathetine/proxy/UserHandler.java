package com.cathetine.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author xjk
 * @date 2020/8/16 -  22:50
 **/
public class UserHandler implements InvocationHandler {

    private Object object;

    public UserHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(proxy);
        System.out.println(object);
        method.invoke(object, args);
        return null;
    }
}
