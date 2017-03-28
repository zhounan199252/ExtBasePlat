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
 * 角色表
 * Created by yaonan on 2015/12/18.
 */
@Entity
@Table(name = "BASE_ROLEINFO")
public class BaseRoleInfo implements Serializable {
    /**
     * 序列化
     */
    private static  final  long serialVersionUID= 1L;
    @Id()
    @GenericGenerator(name="systemUUID",strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name ="OID")
    @JsonView(BaseView.Summary.class)
    private String oid;
//    角色编号
@Column(name ="CODE")
@JsonView(BaseView.RolePermissionSummary.class)
    private String code;
//    角色名称
@Column(name ="NAME")
@JsonView(BaseView.Summary.class)
    private String name;
//    状态
@Column(name ="STATE")
@JsonView(BaseView.RolePermissionSummary.class)
    private Integer state;
//    创建时间
@Column(name ="CREATETIME")
@JsonView(BaseView.RolePermissionSummary.class)
    private Date createTime;

    @Transient
    private String permissionsId;

    //用户
    @Transient
    @ManyToMany
    @JoinTable(name = "BASE_RELATION_USERROLE",joinColumns = {@JoinColumn(name="ROLEID")},inverseJoinColumns = {@JoinColumn(name="USERID")})
    private List<BaseUserInfo> users;
    //组织
    @JsonView(BaseView.RolePermissionSummary.class)
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "BASE_RELATION_ROLEORG",joinColumns = {@JoinColumn(name="ROLEID")},inverseJoinColumns = {@JoinColumn(name="ORGID")})
    private List<BaseOrgInfo> orgs;
    //权限
    @JsonView(BaseView.RolePermissionSummary.class)
    @ManyToMany
    @JoinTable(name = "BASE_RELATION_ROLEPERMISSION",joinColumns = {@JoinColumn(name="ROLEID")},inverseJoinColumns = {@JoinColumn(name="PERMISSIONID")})
    private List<BasePermissionInfo> permissions;

    public BaseRoleInfo() {

    }
    public BaseRoleInfo(String oid) {
         this.oid=oid;
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
