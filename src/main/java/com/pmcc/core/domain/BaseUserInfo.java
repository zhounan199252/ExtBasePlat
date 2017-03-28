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
 * 基础用户表
 */
@Entity
@Table(name = "BASE_USERINFO")
public class BaseUserInfo  implements Serializable{
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
	//名称
	@Column(name ="NAME")
	@JsonView(BaseView.UserOrgSummary.class)
	private String name;
	//用户名
	@Column(name ="USERNAME")
	@JsonView(BaseView.UserOrgSummary.class)
	private String userName;
	//用户密码
	@Column(name ="USERPWD")
	@JsonView(BaseView.UserOrgSummary.class)
	private String userPwd;
	//状态
	@Column(name ="STATE")
	@JsonView(BaseView.UserOrgSummary.class)
	private Integer state;
	//创建时间
	@Column(name ="CREATETIME")
	@JsonView(BaseView.UserOrgSummary.class)
	private Date createTime;
	@Transient
	public String orgsId;
   //组织
	//joinTable name属性指定之间表名称
   @JsonView(BaseView.UserOrgSummary.class)
   @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
   @JoinTable(name = "BASE_RELATION_USERORG",joinColumns = {@JoinColumn(name="USERID")},inverseJoinColumns = {@JoinColumn(name="ORGID")})
	private List<BaseOrgInfo> orgs;
  //角色
  @JsonView(BaseView.UserOrgSummary.class)
 @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
 @JoinTable(name = "BASE_RELATION_USERROLE",joinColumns = {@JoinColumn(name="USERID")},inverseJoinColumns = {@JoinColumn(name="ROLEID")})
	private List<BaseRoleInfo> roles;

	public BaseUserInfo(String oid) {
		this.oid = oid;
	}

	public BaseUserInfo() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
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

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public List<BaseOrgInfo> getOrgs() {
		return orgs;
	}

	public void setOrgs(List<BaseOrgInfo> orgs) {
		this.orgs = orgs;
	}

	public List<BaseRoleInfo> getRoles() {
		return roles;
	}

	public void setRoles(List<BaseRoleInfo> roles) {
		this.roles = roles;
	}

	public String getOrgsId() {
		return orgsId;
	}

	public void setOrgsId(String orgsId) {
		this.orgsId = orgsId;
	}
}
