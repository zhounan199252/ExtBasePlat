package com.pmcc.core.domain;

import com.pmcc.core.domain.BaseModuleInfo;
import com.pmcc.core.domain.BaseOrgInfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 组织机构和菜单功能关系表
 * Created by yaonan on 2015/12/18.
 */
@Entity
@Table(name = "BASE_RELATION_ORGMODULE")
public class BaseRelationOrgModule {
    @Id()
    @Column(name ="OID")
    private String oid;
    //组织机构id
    @Column(name ="ORGID")
    private String orgId;
//    菜单功能id
    @Column(name ="MODULEID")
    private String moduleId;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }
}
