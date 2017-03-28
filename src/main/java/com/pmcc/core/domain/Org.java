package com.pmcc.core.domain;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Admin on 2016/1/27.
 */
@Entity
@Table(name = "ORG")
@Cache(region = "com.pmcc.core.domain.Org", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Org implements Serializable {

    private String id;
    private String orgCName;
    private String orgEName;
    private String remark;

    private Set<OrgPersonRelation> organPersonRelationInfo = new HashSet<OrgPersonRelation>();

    @Id
    @GenericGenerator(name="systemUUID",strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name ="OID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name ="ORG_CNAME")
    public String getOrgCName() {
        return orgCName;
    }

    public void setOrgCName(String orgCName) {
        this.orgCName = orgCName;
    }

    @Column(name ="ORG_ENAME")
    public String getOrgEName() {
        return orgEName;
    }

    public void setOrgEName(String orgEName) {
        this.orgEName = orgEName;
    }

    @Column(name ="REMARK")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "org")
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public Set<OrgPersonRelation> getOrganPersonRelationInfo() {
        return organPersonRelationInfo;
    }

    public void setOrganPersonRelationInfo(Set<OrgPersonRelation> organPersonRelationInfo) {
        this.organPersonRelationInfo = organPersonRelationInfo;
    }
}
