package com.pmcc.core.web;
import com.fasterxml.jackson.annotation.JsonView;
import com.pmcc.core.domain.BasePermissionInfo;
import com.pmcc.core.domain.BaseRoleInfo;
import com.pmcc.core.manager.managerfacade.BasePermissionInfoManagerFacade;
import com.pmcc.core.manager.managerfacade.BaseRoleInfoManagerFacade;
import com.pmcc.jsonView.BaseView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("roleInfo")
public class BaseRoleInfoController {

	@Autowired
	BaseRoleInfoManagerFacade roleInfoManager;

	@RequestMapping(value = "/getRoleList", method = RequestMethod.GET)
	@ResponseBody
	@JsonView(BaseView.RolePermissionSummary.class)
	public Map<String, Object> getRoleList(int page, int limit,String swhere,String sort,String type,String xtype){
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("success",true);
		//type=null 分页，type=ture 不分页
		res.put("extData",roleInfoManager.getRoleList(page, limit, swhere, sort, type,xtype));
		res.put("total",roleInfoManager.getRoleList(page, limit, swhere, sort, "true",xtype).size());
		res.put("curPage",page);
		return res;
	}
	@RequestMapping(value = "/postSave", method = RequestMethod.POST)
	@ResponseBody
	public Boolean postSave(BaseRoleInfo roleInfo) {
		Boolean isExist=false;
		isExist=roleInfoManager.save(roleInfo);
		return isExist;
	}
	@RequestMapping(value = "/rolePermissionSave", method = RequestMethod.POST)
	@ResponseBody
	public Boolean rolePermissionSave(String oid,String permissionsId,String type) {
		//批量增加和批量刪除
		Boolean isExist=false;
		isExist=roleInfoManager.saveOrUpdatePermission(oid,permissionsId,type);
		return isExist;
	}
	@RequestMapping(value = "/deleteById", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteById(String  PostData) {
		return roleInfoManager.deleteById(PostData);
	}


}
