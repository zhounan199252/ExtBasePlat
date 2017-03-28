package com.pmcc.core.web;
import com.fasterxml.jackson.annotation.JsonView;
import com.pmcc.core.domain.BasePermissionInfo;
import com.pmcc.core.manager.managerfacade.BaseOrgInfoManagerFacade;
import com.pmcc.core.manager.managerfacade.BasePermissionInfoManagerFacade;
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
@RequestMapping("permissonInfo")
public class BasePermissionInfoController {

	@Autowired
	BasePermissionInfoManagerFacade permissionInfoManager;

	@RequestMapping(value = "/getPermissionList", method = RequestMethod.GET)
	@ResponseBody
	@JsonView(BaseView.PermissionSummary.class)
	public List<BasePermissionInfo> getPermissionList(int page,int limit,String type){
		return permissionInfoManager.getPermissionList(page,limit,null,null,type);
	}
	@RequestMapping(value = "/getAllPermissionList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllPermissionList(int page, int limit,String swhere,String sort,String type){
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("success",true);
		//type=null 分页，type=ture 不分页
		res.put("total",permissionInfoManager.getPermissionList(page, limit, swhere, sort, "true").size());
		res.put("curPage",page);
		res.put("extData",permissionInfoManager.getPermissionList(page, limit, swhere, sort, type));
		return res;
	}
	@RequestMapping(value = "/getPermissionListNoPage", method = RequestMethod.GET)
	@ResponseBody
	public List<BasePermissionInfo> getPermissionListNoPage(String type){
		return permissionInfoManager.getPermissionList(0,0,null,null,type);
	}
	@RequestMapping(value = "/postSave", method = RequestMethod.POST)
	@ResponseBody
	public Boolean postSave(BasePermissionInfo permissionInfo) {
		Boolean isExist=false;
		isExist=permissionInfoManager.save(permissionInfo);
		return isExist;
	}
	@RequestMapping(value = "/deleteById", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteById(String  PostData) {
		return permissionInfoManager.deleteById(PostData);
	}


}
