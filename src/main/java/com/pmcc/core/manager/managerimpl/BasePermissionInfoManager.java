package com.pmcc.core.manager.managerimpl;

import com.pmcc.core.dao.daofacade.BasePermissionInfoDaoFacade;
import com.pmcc.core.dao.daofacade.BaseUserInfoDaoFacade;
import com.pmcc.core.domain.BasePermissionInfo;
import com.pmcc.core.domain.BaseUserInfo;
import com.pmcc.core.manager.managerfacade.BasePermissionInfoManagerFacade;
import com.pmcc.core.manager.managerfacade.BaseUserInfoManagerFacade;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository("permissionInfoManager")
public class BasePermissionInfoManager implements BasePermissionInfoManagerFacade {

	@Resource
	private BasePermissionInfoDaoFacade basePermissionInfoDao;


	@Override
	public List<BasePermissionInfo> getPermissionList(int page, int limit, String swhere, String sort, String type) {
		return basePermissionInfoDao.getPermissionList(page,limit,swhere,sort,type);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean save(BasePermissionInfo permissionInfo) {
		permissionInfo.setCreateTime(new Date());
		return basePermissionInfoDao.saveOrUpdate(permissionInfo);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> deleteById(String postData) {
		//分割postData
		String[] id=postData.split(",");
		int succount=0;
		int errorcount=0;
		for(int i=0;i<id.length;i++){
			if(basePermissionInfoDao.deleteById(id[i])){
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
}
