package com.cathetine.spring;

import com.cathetine.model.User;
import com.cathetine.spring.lifecycle.SpringUser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xjk
 * @date 2020/7/19 -  22:01
 **/
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("application-context.xml");

        SpringUser user = (SpringUser) applicationContext.getBean("user");

        System.out.println(user.toString());

        System.out.println("现在开始关闭容器！");
        ((ClassPathXmlApplicationContext)applicationContext).registerShutdownHook();
    }
}
