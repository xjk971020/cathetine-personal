package com.cathetine.aboutJDK;

import com.cathetine.model.Role;
import com.cathetine.model.RoleDescription;
import com.cathetine.model.User;
import org.junit.Test;

/**
 * @author xjk
 * @date 2020/8/16 -  10:24
 **/
public class CloneTest {
    @Test
    public void test1() throws CloneNotSupportedException {
        RoleDescription roleDescription = new RoleDescription();

        Role role = new Role();
        role.setRoleName("admin");
        role.setRoleDecription(roleDescription);

        User user = new User();
        user.setUserName("cathe");
        user.setPhone("1234567890");
        user.setSex(1);
        user.setRole(role);

        User userClone =(User) user.clone();

        System.out.println("userClone: " + userClone);
        System.out.println("user: " + user);

        System.out.println();

        System.out.println("userClone.hashCode: " + userClone.hashCode());
        System.out.println("user.hashCode: " + user.hashCode());

        System.out.println();

        System.out.println("userClone.sex.hashCode:" + userClone.getSex().hashCode());
        System.out.println("user.sex.hashCode:" + user.getSex().hashCode());

        System.out.println();

        System.out.println("userClone.userName.hashCode:" + userClone.getUserName().hashCode());
        System.out.println("user.userName.hashCode:" + userClone.getUserName().hashCode());

        System.out.println();

        System.out.println("userClone.role: " + userClone.getRole());
        System.out.println("user.role: " + user.getRole());

        System.out.println();

        System.out.println("userClone.role.hashCode: " + userClone.getRole().hashCode());
        System.out.println("user.role.hashCode: " + user.getRole().hashCode());

        System.out.println();

        System.out.println("userClone.role.roleDescription.hashCode: " + userClone.getRole().getRoleDecription().hashCode());
        System.out.println("user.role.roleDescription.hashCode: " + user.getRole().getRoleDecription().hashCode());
    }

    @Test
    public void test() throws CloneNotSupportedException {
        RoleDescription roleDescription = new RoleDescription();

        Role role = new Role();
        role.setRoleName("admin");
        role.setRoleDecription(roleDescription);

        User user = new User();
        user.setUserName("cathe");
        user.setPhone("1234567890");
        user.setSex(1);
        user.setRole(role);

        User userClone =(User) user.clone();

        System.out.println("userClone.role.roleDescription.hashCode: " + userClone.getRole().getRoleDecription().hashCode());
        System.out.println("user.role.roleDescription.hashCode: " + user.getRole().getRoleDecription().hashCode());
    }
}
