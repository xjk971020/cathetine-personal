package com.cathetine.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

/**
 * @author xjk
 * @Description:Spring测试实体
 * @date 2020/7/21 -  21:35
 **/
public class SpringUser implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {

    private String username;

    private String phone;

    private String beanName;

    private BeanFactory beanFactory;

    public SpringUser() {
        System.out.println("开始执行SpringUser类的构造方法....");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        System.out.println("【注入属性】注入属性username");
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        System.out.println("【注入属性】注入属性phone");
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "SpringUser{" +
                "username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("【BeanFactoryAware接口】调用BeanFactoryAware.setBeanFactory()");
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("【BeanNameAware接口】调用BeanNameAware.setBeanName()");
        this.beanName = name;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("【DiposibleBean接口】调用DisposableBean.destory()");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("【InitializingBean接口】调用InitializingBean.afterPropertiesSet()");
    }

    public void springUserIinit() {
        System.out.println("【init-method】调用<bean>的init-method属性指定的初始化方法");
    }

    public void springUserDestory() {
        System.out.println("【destroy-method】调用<bean>的destroy-method属性指定的初始化方法");
    }
}
