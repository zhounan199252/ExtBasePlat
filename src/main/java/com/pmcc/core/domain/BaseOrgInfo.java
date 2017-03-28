package com.pmcc.core.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pmcc.jsonView.BaseView;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 组织机构表
 * Created by yaonan on 2015/12/17.
 */
@Entity
@Table(name = "BASE_ORGINFO")
public class BaseOrgInfo implements Serializable {
    /**
     * 序列化
     */
    private static  final  long serialVersionUID= 1L;
   //主键
   @Id()
   @GenericGenerator(name="systemUUID",strategy = "uuid")
   @GeneratedValue(generator = "systemUUID")
   @Column(name ="OID")
   @JsonView(BaseView.UserOrgSummary.class)
    private String oid;
    //机构编号
    @Column(name ="CODE")
    private String code;
    //机构名称
    @Column(name ="NAME")
    @JsonView(BaseView.UserOrgSummary.class)
    private String name;
    //机构说明
    @Column(name ="DESCRIPTION")
    private String description;
    //上级机构编号
    @Column(name ="PARENTOID")
    private String parentOid;
    //左根
    @Column(name ="LT")
    private Integer LT;
    //右根
    @Column(name ="RT")
    private Integer RT;
    //树形层级
    @Column(name ="TREELEVEL")
    private Integer  treeLevel;
    @Column(name ="ORGTYPE")
    private Integer orgType;
    //机构级别
    @Column(name ="ORGLEVEL")
    private Integer  orgLevel;
    //机构号
    @Column(name ="ORGNO")
    private Integer orgNo;
//    机构英文名称
@Column(name ="ENGLISHNAME")
    private String  englishName;
//    机构简称
@Column(name ="SHORTNAME")
    private String shortName;
//    排序编号
@Column(name ="SORTCODE")
    private Integer sortCode;
//    状态
@Column(name ="STATE")
    private Integer  state;
//    创建时间
@Column(name ="CREATETIME")
    private Date createTime;
    //组织机构和人多对对映射
    @Transient
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "BASE_RELATION_USERORG",joinColumns = {@JoinColumn(name="ORGID")},inverseJoinColumns = {@JoinColumn(name="USERID")})
    private List<BaseUserInfo> users;
    //角色
    @Transient
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "BASE_RELATION_ROLEORG",joinColumns = {@JoinColumn(name="ORGID")},inverseJoinColumns = {@JoinColumn(name="ROLEID")})
    private List<BaseRoleInfo> roles;
    //菜单
    @Transient
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "BASE_RELATION_ORGMODULE",joinColumns = {@JoinColumn(name="ORGID")},inverseJoinColumns = {@JoinColumn(name="MODULEID")})
    private List<BaseModuleInfo> modules;

    public BaseOrgInfo(String oid) {
        this.oid = oid;
    }

    public BaseOrgInfo() {
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParentOid() {
        return parentOid;
    }

    public void setParentOid(String parentOid) {
        this.parentOid = parentOid;
    }

    public Integer getLT() {
        return LT;
    }

    public void setLT(Integer LT) {
        this.LT = LT;
    }

    public Integer getRT() {
        return RT;
    }

    public void setRT(Integer RT) {
        this.RT = RT;
    }

    public Integer getTreeLevel() {
        return treeLevel;
    }

    public void setTreeLevel(Integer treeLevel) {
        this.treeLevel = treeLevel;
    }

    public Integer getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(Integer orgLevel) {
        this.orgLevel = orgLevel;
    }

    public Integer getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(Integer orgNo) {
        this.orgNo = orgNo;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }

    public Integer getSortCode() {
        return sortCode;
    }

    public void setSortCode(Integer sortCode) {
        this.sortCode = sortCode;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
    @JsonSerialize(using = com.pmcc.utils.CustomDateSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<BaseUserInfo> getUsers() {
        return users;
    }

    public void setUsers(List<BaseUserInfo> users) {
        this.users = users;
    }

    public List<BaseRoleInfo> getRoles() {
        return roles;
    }

    public void setRoles(List<BaseRoleInfo> roles) {
        this.roles = roles;
    }

    public List<BaseModuleInfo> getModules() {
        return modules;
    }

    public void setModules(List<BaseModuleInfo> modules) {
        this.modules = modules;
    }
}
