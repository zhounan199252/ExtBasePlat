package com.pmcc.core.manager.managerfacade;

import com.pmcc.core.domain.BaseModuleInfo;
import com.pmcc.core.domain.BasePermissionInfo;
import com.pmcc.utils.ModuleTreeNode;
import com.pmcc.utils.OrgMeduleTreeNode;

import java.util.List;
import java.util.Map;

public interface BaseModuleInfoManagerFacade {
	/**
	 * 根据用户和组织查询菜单
	 * @param userId
	 * @param orgId
	 * @return
	 */
			public List<BaseModuleInfo> queryUserModule(String userId,String  orgId);

			public	List<BaseModuleInfo> queryUserButtons(String moduleEname);

	public List<BaseModuleInfo> queryModuleByPid(String pid);


	public ModuleTreeNode queryAllModule() ;

	List<BasePermissionInfo> getModulePermissionByOid(String oid);

	Boolean isExist(String oid, String val);

	Boolean save(BaseModuleInfo moduleInfo);

	Boolean moduleTreeMove(String oid, String moveType);

	Map<String,Object> deleteById(String postData);

	Boolean saveOrUpdatePermission(String oid, String permissionsId, String type);

	OrgMeduleTreeNode getOrgModulesList(String orgId);

	Boolean updateRelaModuleOrg(String orgId, String moduleIds);

	public List<BaseModuleInfo> getModuleList() ;
	public List<ModuleTreeNode> findByPid(String orgId,String flag);
}
