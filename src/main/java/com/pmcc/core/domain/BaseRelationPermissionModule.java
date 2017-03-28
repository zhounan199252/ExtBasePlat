package com.pmcc.core.domain;

import com.pmcc.core.domain.BaseModuleInfo;
import com.pmcc.core.domain.BasePermissionInfo;

/**
 * 权限功能关系表
 * Created by yaonan on 2015/12/18.
 */
public class BaseRelationPermissionModule {
    private String oid;
    //菜单功能id
    private BaseModuleInfo moduleId;
    //权限功能id
    private BasePermissionInfo permissionId;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public BaseModuleInfo getModuleId() {
        return moduleId;
    }

    public void setModuleId(BaseModuleInfo moduleId) {
        this.moduleId = moduleId;
    }

    public BasePermissionInfo getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(BasePermissionInfo permissionId) {
        this.permissionId = permissionId;
    }
}
