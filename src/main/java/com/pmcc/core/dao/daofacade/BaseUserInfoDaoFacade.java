package com.pmcc.core.dao.daofacade;


import com.pmcc.core.domain.BaseUserInfo;

import java.util.List;

/**
 * Created by yaonan on 2015/12/21.
 */
public interface BaseUserInfoDaoFacade {
    public List<BaseUserInfo> queryByLogin(BaseUserInfo obj);

    public  List<BaseUserInfo> getUserList(int page, int limit, String swhere, String sort, String type, String xtype);

   public BaseUserInfo findByUserName(String val);

  public  Boolean saveOrUpdate(BaseUserInfo userInfo);

  public  boolean deleteById(String id);

    Boolean saveUserRoleRelation(String users, String roles);
}
