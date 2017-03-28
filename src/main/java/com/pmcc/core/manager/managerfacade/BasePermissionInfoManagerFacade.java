package com.pmcc.core.manager.managerfacade;

import com.pmcc.core.domain.BasePermissionInfo;
import com.pmcc.core.domain.BaseUserInfo;

import java.util.List;
import java.util.Map;

public interface BasePermissionInfoManagerFacade {

	public List<BasePermissionInfo> getPermissionList(int page, int limit, String swhere, String sort, String type);
	public Boolean save(BasePermissionInfo permissionInfo);

	public Map<String,Object> deleteById(String postData);
}
