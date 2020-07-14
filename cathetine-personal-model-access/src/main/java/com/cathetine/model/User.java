package com.cathetine.model;

/**
 * @Author:xjk
 * @Description:测试实体
 * @Date: 2020/7/14
 */
public class User {
    private Long id;
    private String userName;
    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
