package com.pmcc.core.dao.daofacade;


import com.pmcc.core.domain.BasePermissionInfo;
import com.pmcc.core.domain.BaseUserInfo;

import java.util.List;

/**
 * Created by yaonan on 2015/12/21.
 */
public interface BasePermissionInfoDaoFacade {

   public  List<BasePermissionInfo> getPermissionList(int page, int limit, String swhere, String sort, String type);

   public BasePermissionInfo find(String s);
   public  Boolean saveOrUpdate(BasePermissionInfo permissionInfo);

   public   boolean deleteById(String s);
}
