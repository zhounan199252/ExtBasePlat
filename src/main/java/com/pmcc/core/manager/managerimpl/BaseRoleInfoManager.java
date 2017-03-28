package com.pmcc.core.manager.managerimpl;

import com.pmcc.core.dao.daofacade.BasePermissionInfoDaoFacade;
import com.pmcc.core.dao.daofacade.BaseRoleInfoDaoFacade;
import com.pmcc.core.domain.BaseOrgInfo;
import com.pmcc.core.domain.BasePermissionInfo;
import com.pmcc.core.domain.BaseRoleInfo;
import com.pmcc.core.manager.managerfacade.BaseRoleInfoManagerFacade;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


@Repository("roleInfoManager")
public class BaseRoleInfoManager implements BaseRoleInfoManagerFacade {

	@Resource
	private BaseRoleInfoDaoFacade baseRoleInfoDao;
	@Resource
	private BasePermissionInfoDaoFacade basePermissionInfoDao;

			@Override
			@Transactional(readOnly = true)
			public List<BaseRoleInfo> getRoleList(int page, int limit, String swhere, String sort, String type, String xtype) {
			//	if("org".equals(xtype)){

					List<BaseRoleInfo>  list=	baseRoleInfoDao.getRoleList(page, limit, swhere, sort, type,xtype);
					//去除组织下的菜单和权限
						for (BaseRoleInfo roleInfo : list) {
							//roleInfo.setPermissions(null);
							for (BaseOrgInfo orgInfo : roleInfo.getOrgs()) {
								orgInfo.setUsers(null);
								orgInfo.setRoles(null);
								orgInfo.setModules(null);
							}
//							for(BasePermissionInfo permissionInfo:roleInfo.getPermissions()){
//
//							}
						}
					return list;
//				}else{
//					List<BaseRoleInfo>  list=	baseRoleInfoDao.getRoleList(page, limit, swhere, sort, type);
//					//去除组织
//					for(BaseRoleInfo roleInfo:list){
//						roleInfo.setOrgs(null);
//					}
//					return list;
//				}
			}

		@Override
		@Transactional(propagation = Propagation.REQUIRED)
		public Boolean save(BaseRoleInfo roleInfo) {
			roleInfo.setCreateTime(new Date());
			return baseRoleInfoDao.saveOrUpdate(roleInfo);
		}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> deleteById(String postData) {
		//分割postData
		String[] id=postData.split(",");
		int succount=0;
		int errorcount=0;
		for(int i=0;i<id.length;i++){
			if(baseRoleInfoDao.deleteById(id[i])){
				succount++;
			}else {
				errorcount++;
			};
		}
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("result",true);
		res.put("message","处理成功！");
		res.put("succount",succount);
		res.put("errorcount",errorcount);
		return res;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean saveOrUpdatePermission(String oid, String permissionsId, String type) {
		//把oid分組
		String[] id=oid.split(",");
		String[] pid=permissionsId.split(",");
		List<BasePermissionInfo> listCurrent=new ArrayList<BasePermissionInfo>();
		List<BasePermissionInfo> list=new ArrayList<BasePermissionInfo>();
		//根據權限id查詢權限的對象信息
		for(int j=0;j<pid.length;j++){
			BasePermissionInfo basePermissionInfo=	basePermissionInfoDao.find(pid[j]);
			list.add(basePermissionInfo);
		}
		for(int i=0;i<id.length;i++){
			//根據id查出當前的最新的角色信息
			BaseRoleInfo baseRoleInfo=baseRoleInfoDao.find(id[i]);
			listCurrent=baseRoleInfo.getPermissions();
			if(type.equals("add")){
				for(int m=0;m<list.size();m++){
					if(!listCurrent.contains(list.get(m))){
						listCurrent.add(list.get(m));
					}
				}
			}else{
				for(int m=0;m<list.size();m++){
					if(listCurrent.contains(list.get(m))){
						listCurrent.remove(list.get(m));
					}
				}
			}
			baseRoleInfo.setPermissions(listCurrent);
			baseRoleInfoDao.saveOrUpdatePermission(baseRoleInfo);
		}
		return true;
	}


}
