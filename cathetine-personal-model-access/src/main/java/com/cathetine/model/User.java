package com.cathetine.model;

import java.util.function.DoubleFunction;
import java.util.function.Predicate;

/**
 * @Author:xjk
 * @Description:测试实体
 * @Date: 2020/7/14
 */
public class User implements Cloneable{
    private Long id;
    private String userName;
    private Integer sex;
    private String phone;
    private Double amt;
    private String nation;
    private Role role;

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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isPolice() {
        return "110".equals(phone);
    }

    public Double getAmt() {
        return amt;
    }

    public void setAmt(Double amt) {
        this.amt = amt;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public User setAmtFunction(DoubleFunction<User> amtFunction) {
        return amtFunction.apply(getAmt());
    }

    public boolean isRichMan(Predicate<User> predicateRich, Predicate<User> predicateMan) {
        boolean isRich = predicateRich.test(this);
        boolean isMan = predicateMan.test(this);
        return isMan && isRich;
    }

    public boolean isRich() {
        return getAmt() > 1000000;
    }

    public boolean isMan() {
        return getSex().equals(1);
    }

    public String desc() {
        if (this.getSex().equals(1)) {
            return getUserName() + " is man.";
        } else if (this.getSex().equals(2)){
            return getUserName() + " is woman.";
        } else {
            throw new RuntimeException("param[sex] is wrong.");
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", sex=" + sex +
                ", phone='" + phone + '\'' +
                ", amt=" + amt +
                ", role=" + role +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        User clone = (User)super.clone();
        Role roleClone = (Role) role.clone();
        clone.setRole(roleClone);
        return clone;
    }
}
