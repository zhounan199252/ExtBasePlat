package com.pmcc.base.entity;

import com.pmcc.core.domain.BaseOrgInfo;
import com.pmcc.core.domain.BaseUserInfo;
import com.pmcc.core.domain.Org;
import com.pmcc.core.domain.Person;
import org.hibernate.annotations.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 基础类
 * Created by Admin on 2016/1/25.
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    protected String id; // 主键
    protected Person creator; // 创建人
    protected Date createTime; // 创建时间
    protected Org creatorUnit; // 创建人单位


    public BaseEntity() {
    }

    @Id
    @GenericGenerator(name="systemUUID",strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name = "OID")
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATOR")
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public Person getCreator() {
        return creator;
    }

    public void setCreator(Person creator) {
        this.creator = creator;
    }

    @Column(name = "CREATE_TIME")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATOR_UNIT")
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public Org getCreatorUnit() {
        return creatorUnit;
    }

    public void setCreatorUnit(Org creatorUnit) {
        this.creatorUnit = creatorUnit;
    }
}
