package com.cathetine.spring;

import com.cathetine.spring.schema.User;
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

        User user = (User) applicationContext.getBean("cat");

        System.out.println(user.toString());

        System.out.println("现在开始关闭容器！");
        ((ClassPathXmlApplicationContext)applicationContext).registerShutdownHook();
    }
}
