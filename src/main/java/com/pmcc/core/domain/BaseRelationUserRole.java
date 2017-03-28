package com.pmcc.core.domain;

/**
 * 用户角色关系表
 * Created by yaonan on 2015/12/18.
 */
public class BaseRelationUserRole {
    private  String oid;
    //用户id
    private BaseUserInfo userId;
    //角色Id
    private BaseRoleInfo roleId;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public BaseUserInfo getUserId() {
        return userId;
    }

    public void setUserId(BaseUserInfo userId) {
        this.userId = userId;
    }

    public BaseRoleInfo getRoleId() {
        return roleId;
    }

    public void setRoleId(BaseRoleInfo roleId) {
        this.roleId = roleId;
    }
}
