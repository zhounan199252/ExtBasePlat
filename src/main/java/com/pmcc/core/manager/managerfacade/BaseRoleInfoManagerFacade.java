package com.pmcc.core.manager.managerfacade;

import com.pmcc.core.domain.BaseRoleInfo;

import java.util.List;
import java.util.Map;

public interface BaseRoleInfoManagerFacade {

			public  List<BaseRoleInfo> getRoleList(int page, int limit, String swhere, String sort, String type, String xtype);

			public Boolean save(BaseRoleInfo roleInfo);

			public Map<String,Object> deleteById(String postData);

	public Boolean saveOrUpdatePermission(String oid, String permissionsId, String type);
}
