package com.pmcc.core.domain;

/**
 * 角色和组织机构关系表
 * Created by yaonan on 2015/12/18.
 */
public class BaseRelationRoleOrg {
    private String oid;
    //角色id
    private String roleId;
    //组织机构id
    private String orgId;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
