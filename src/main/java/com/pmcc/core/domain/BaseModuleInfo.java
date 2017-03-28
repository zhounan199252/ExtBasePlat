package com.pmcc.core.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 菜单功能表
 * Created by yaonan on 2015/12/18.
 */
@Entity
@Table(name = "BASE_MODULEINFO")
public class BaseModuleInfo implements Serializable {
    /**
     * 序列化
     */
    private static  final  long serialVersionUID= 1L;
//    主键
@Id()
@GenericGenerator(name="systemUUID",strategy = "uuid")
@GeneratedValue(generator = "systemUUID")
@Column(name ="OID")
    private String oid;
    //    编号
    @Column(name ="CODE")
    private String code;
    //    名称
    @Column(name ="NAME")
    private String name;
    //    英文名称
    @Column(name ="ENAME")
    private String ename;
//    图标
@Column(name ="ICO")
    private String ico;
//    操作方法
@Column(name ="PATHHANDLER")
    private String pathHandler;
    //描述
    @Column(name ="DESCRIPTION")
    private String description;
    //action路径
    @Column(name ="URL")
    private String url;
    //上级菜单
    @Column(name ="PARENTOID")
    private String parentOid;
    //左根
    @Column(name ="LT")
    private Integer LT;
    //右根
    @Column(name ="RT")
    private Integer RT;
    //树层级
    @Column(name ="TREELEVEL")
    private Integer treeLevel;
    //排序编号
    @Column(name ="SORTCODE")
    private Integer sortCode;
    //状态
    @Column(name ="STATE")
    private Integer state;
    //标识
    @Column(name ="FLAG")
    private Integer flag;
    //按钮id
    @Column(name ="BUTTONID")
    private String buttonId;
    //创建时间
    @Column(name ="CREATETIME")
    private Date createTime;
//组织
@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
@JoinTable(name = "BASE_RELATION_ORGMODULE",joinColumns = {@JoinColumn(name="MODULEID")},inverseJoinColumns = {@JoinColumn(name="ORGID")})
    private List<BaseOrgInfo> orgs;
//权限
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "BASE_RELATION_PERMISSIONMODULE",joinColumns = {@JoinColumn(name="MODULEID")},inverseJoinColumns = {@JoinColumn(name="PERMISSIONID")})
    private List<BasePermissionInfo> permissions;
    @Transient
    private String permissionsId;

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

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getPathHandler() {
        return pathHandler;
    }

    public void setPathHandler(String pathHandler) {
        this.pathHandler = pathHandler;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getButtonId() {
        return buttonId;
    }

    public void setButtonId(String buttonId) {
        this.buttonId = buttonId;
    }
    @JsonSerialize(using = com.pmcc.utils.CustomDateSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<BaseOrgInfo> getOrgs() {
        return orgs;
    }

    public void setOrgs(List<BaseOrgInfo> orgs) {
        this.orgs = orgs;
    }

    public List<BasePermissionInfo> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<BasePermissionInfo> permissions) {
        this.permissions = permissions;
    }

    public String getPermissionsId() {
        return permissionsId;
    }

    public void setPermissionsId(String permissionsId) {
        this.permissionsId = permissionsId;
    }
}
