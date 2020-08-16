package com.cathetine.model;

/**
 * @Author:xjk
 * @Description:
 * @Date: 2020/7/15
 */
public class Role implements Cloneable{
    private String roleName;
    private RoleDescription roleDecription;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public RoleDescription getRoleDecription() {
        return roleDecription;
    }

    public void setRoleDecription(RoleDescription roleDecription) {
        this.roleDecription = roleDecription;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleName='" + roleName + '\'' +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}

