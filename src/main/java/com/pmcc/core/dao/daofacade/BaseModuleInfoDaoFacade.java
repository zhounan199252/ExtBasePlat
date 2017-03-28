package com.pmcc.core.dao.daofacade;


import com.pmcc.core.domain.BaseModuleInfo;
import com.pmcc.core.domain.BaseOrgInfo;
import com.pmcc.core.domain.BasePermissionInfo;
import com.pmcc.core.domain.BaseUserInfo;

import java.util.List;

/**
 * Created by yaonan on 2015/12/21.
 */
public interface BaseModuleInfoDaoFacade {
    public List<BaseModuleInfo> queryUserModule(String userId,String orgId);

   public List<BaseModuleInfo> queryUserButtons(String moduleEname);

   public List<BaseModuleInfo> queryModuleByPid(String pid);

   public List<BaseModuleInfo> queryModule(int treelevel,String pid);

    List<BasePermissionInfo> getModulePermissionByOid(String oid);

    BaseModuleInfo findByEname(String val);

    Boolean saveOrUpdate(BaseModuleInfo moduleInfo);

    Boolean moduleTreeMove(String oid, String moveType);

    boolean deleteById(String s);

    BaseModuleInfo find(String s);

    void saveOrUpdatePermission(BaseModuleInfo baseModuleInfo);

    boolean isOrgRelaModule(String oid, String orgId);

    Boolean updateRelaModuleOrg(String orgId, String moduleIds);

    public List<BaseModuleInfo> getModuleList();
    public List<BaseModuleInfo> query(int treelevel, String pid,String flag);
}
