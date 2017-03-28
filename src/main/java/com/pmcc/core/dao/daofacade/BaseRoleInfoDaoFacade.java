package com.pmcc.core.dao.daofacade;


import com.pmcc.core.domain.BaseRoleInfo;

import java.util.List;

/**
 * Created by yaonan on 2015/12/21.
 */
public interface BaseRoleInfoDaoFacade {

    public  List<BaseRoleInfo> getRoleList(int page, int limit, String swhere, String sort, String type);

   public  Boolean saveOrUpdate(BaseRoleInfo roleInfo);

  public   boolean deleteById(String s);

  public   BaseRoleInfo find(String s);

   public  void saveOrUpdatePermission(BaseRoleInfo baseRoleInfo);

    List<BaseRoleInfo> getRoleList(int page, int limit, String swhere, String sort, String type, String xtype);
}
