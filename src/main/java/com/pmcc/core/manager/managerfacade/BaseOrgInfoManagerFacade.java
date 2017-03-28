package com.pmcc.core.manager.managerfacade;

import com.pmcc.core.domain.BaseOrgInfo;
import com.pmcc.utils.OrgTreeNode;
import java.util.List;
import java.util.Map;

public interface BaseOrgInfoManagerFacade {
	//获取父节点下所有的子节点,分步加载
	//public List<BaseOrgInfo> getOrgList(String pid);
	//获取全部组织机构信息
	public  List<BaseOrgInfo> getOrgList();
	//获取该组织结构下的所有节点--用于组织机构完全加载
	//public  List<BaseOrgInfo> getOrgList(String orgId,String type);


	List<OrgTreeNode> findByPid(String type);

	Boolean save(BaseOrgInfo orgInfo);

	Boolean orgTreeMove(String id, String moveType);

	Map<String,Object> deleteById(String postData);

	BaseOrgInfo getOrg(String orgId);
	BaseOrgInfo getOrgInfo(String orgId);

	Boolean orgRoleSave(String orgs, String roles);
}
