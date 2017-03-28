package com.pmcc.core.dao.daofacade;


import com.pmcc.core.domain.BaseModuleInfo;
import com.pmcc.core.domain.BaseOrgInfo;

import java.util.List;

/**
 * Created by yaonan on 2015/12/21.
 */
public interface BaseOrgInfoDaoFacade {
    //获取父节点下所有的子节点,分步加载
    public List<BaseOrgInfo> getOrgList(String pid);
    //获取全部组织机构信息
    public  List<BaseOrgInfo> getOrgList();
    //获取该组织结构下的所有节点--用于组织机构完全加载
    public  List<BaseOrgInfo> getOrgList(String orgId,String type);

    /**
     * 用户列表分页
     * sort排序
     * swhere查询条件
     * @param page
     * @param limit
     * @param swhere
     * @param sort
     * @return
     */
    public  List<BaseOrgInfo> getOrgList(int page,int limit,String swhere,String sort);

    List<BaseOrgInfo> query(int i, String oid);

    Boolean saveOrUpdate(BaseOrgInfo orgInfo);

    Boolean orgTreeMove(String id, String moveType);

    boolean deleteById(String s);

    BaseOrgInfo find(String orgId);
    BaseOrgInfo getOrgInfo(String orgId);

    Boolean orgRoleSave(String orgs, String roles);
}
