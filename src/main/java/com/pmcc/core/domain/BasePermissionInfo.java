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
 * 权限表
 * Created by yaonan on 2015/12/18.
 */
@Entity
@Table(name = "BASE_PERMISSIONINFO")
public class BasePermissionInfo implements Serializable {
    /**
     * 序列化
     */
    private static  final  long serialVersionUID= 1L;
    @Id()
    @GenericGenerator(name="systemUUID",strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name ="OID")
    @JsonView(BaseView.RolePermissionSummary.class)
    private String oid;
    //权限名称
    @Column(name ="NAME")
    @JsonView(BaseView.RolePermissionSummary.class)
    private String name;
    //权限描述
    @Column(name ="DESCRIPTION")
    private String description;
    //创建时间
    @Column(name ="CREATETIME")
    private Date createTime;
    //菜单
    @Transient
    @ManyToMany
    @JoinTable(name = "BASE_RELATION_PERMISSIONMODULE",joinColumns = {@JoinColumn(name="PERMISSIONID")},inverseJoinColumns = {@JoinColumn(name="MODULEID")})
    private List<BaseModuleInfo> modules;
    //角色
    @Transient
    @ManyToMany
    @JoinTable(name = "BASE_RELATION_ROLEPERMISSION",joinColumns = {@JoinColumn(name="PERMISSIONID")},inverseJoinColumns = {@JoinColumn(name="ROLEID")})
    private List<BaseRoleInfo> roles;


    public BasePermissionInfo() {
    }
    public BasePermissionInfo(String oid) {
        this.oid=oid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
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
    @JsonSerialize(using = com.pmcc.utils.CustomDateSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<BaseModuleInfo> getModules() {
        return modules;
    }

    public void setModules(List<BaseModuleInfo> modules) {
        this.modules = modules;
    }

    public List<BaseRoleInfo> getRoles() {
        return roles;
    }

    public void setRoles(List<BaseRoleInfo> roles) {
        this.roles = roles;
    }
}
