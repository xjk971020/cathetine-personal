package com.cathetine.spring.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * bean的命名空间处理器注册器
 */
public class UserNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
    }
}
