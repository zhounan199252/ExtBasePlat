package com.pmcc.core.domain;

import com.pmcc.core.domain.BasePermissionInfo;
import com.pmcc.core.domain.BaseRoleInfo;

/**
 * 角色权限关系表
 * Created by yaonan on 2015/12/18.
 */
public class BaseRelationRolePermission {
    private String oid;
    //角色id
    private BaseRoleInfo roleId;
    //权限id
    private BasePermissionInfo permissionId;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public BaseRoleInfo getRoleId() {
        return roleId;
    }

    public void setRoleId(BaseRoleInfo roleId) {
        this.roleId = roleId;
    }

    public BasePermissionInfo getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(BasePermissionInfo permissionId) {
        this.permissionId = permissionId;
    }
}
