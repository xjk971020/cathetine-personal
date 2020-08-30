package com.cathetine.spring.sourceLearn_01.bean;

import javax.annotation.PostConstruct;

/**
 * @author xjk
 * @date 2020/8/30 -  20:20
 **/
public class Role {
    private String rolename;

    private String phone;

    @PostConstruct
    public void init() {
        System.out.println("Role.class init....");
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
