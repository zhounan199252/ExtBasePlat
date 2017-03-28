package com.pmcc.core.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户和组织机构关系表
 * Created by yaonan on 2015/12/17.
 */

public class BaseRelationUserOrg  implements Serializable{
    /**
     * 序列化
     */
    private static  final  long serialVersionUID= 1L;
    //主键

    private String oid;
//    用户id

    private BaseUserInfo users;
//    组织机构id

    private BaseOrgInfo orgs;

    public BaseRelationUserOrg() {
    }

    public BaseRelationUserOrg(String oid, String usersid, String orgsid) {
        this.oid = oid;
        this.users = new BaseUserInfo(usersid);
        this.orgs = new BaseOrgInfo(orgsid);
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public BaseUserInfo getUsers() {
        return users;
    }

    public void setUsers(BaseUserInfo users) {
        this.users = users;
    }

    public BaseOrgInfo getOrgs() {
        return orgs;
    }

    public void setOrgs(BaseOrgInfo orgs) {
        this.orgs = orgs;
    }
}
