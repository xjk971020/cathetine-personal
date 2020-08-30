package com.cathetine.spring.sourceLearn_01.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author xjk
 * @date 2020/8/30 -  23:02
 **/
@Component
public class Dog {

    @Autowired
    private Cat cat;

    public Dog() {
        System.out.println("Dog constructor");
    }

    @PostConstruct
    public void init() {
        System.out.println("Dog PostConstruct....");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Dog PreDestroy...");
    }

}
