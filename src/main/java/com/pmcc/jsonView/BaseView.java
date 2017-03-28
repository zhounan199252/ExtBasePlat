package com.pmcc.jsonView;

/**
 * Created by yaowe on 2016/1/20.
 */
public class BaseView {
 public    interface Summary {}
 public    interface OrgSummary extends  UserOrgSummary{}
 public    interface UserOrgSummary extends Summary{}
 public    interface RolePermissionSummary extends UserOrgSummary{}
 public    interface PermissionSummary extends  RolePermissionSummary {}

}
