package com.pmcc.core.web;
import com.fasterxml.jackson.annotation.JsonView;
import com.pmcc.core.domain.BaseModuleInfo;
import com.pmcc.core.domain.BaseOrgInfo;
import com.pmcc.core.manager.managerfacade.BaseModuleInfoManagerFacade;
import com.pmcc.core.manager.managerfacade.BaseOrgInfoManagerFacade;
import com.pmcc.jsonView.BaseView;
import com.pmcc.utils.OrgTreeNode;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("baseOrgInfo")
public class BaseOrgInfoController {

	@Autowired
    BaseOrgInfoManagerFacade orgInfoManager;

	/**
	 * 查询所有的组织结构
	 * @param
	 */

	@RequestMapping(value = "/getOrgList", method = RequestMethod.GET)
	@ResponseBody
	@JsonView(BaseView.OrgSummary.class)
	public Map<String, Object> getOrgList(){
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("orgs", orgInfoManager.getOrgList());
        return  model;
	}
/*	@RequestMapping(value = "/getOrgListForTreegrid", method = RequestMethod.GET)
	@ResponseBody
	public OrgTreeNode getOrgListForTreegrid(String id,String type){
			return orgInfoManager.queryAll(type);
	}*/


	@RequestMapping(value = "/getOrgListForTreegrid", method = RequestMethod.GET)
	@ResponseBody
	public List<OrgTreeNode> getOrgListForTreegrid(String orgId,String type){
		return orgInfoManager.findByPid(orgId);
	}
	/**
	 * 获取组织对象，根据主键，不过去除了关联菜单
	 * @param orgId
	 * @return
	 */
	@RequestMapping(value = "/getOrg", method = RequestMethod.GET)
	@ResponseBody
	public BaseOrgInfo getOrg(String orgId){
		return orgInfoManager.getOrg(orgId);
	}

	/**
	 * 保存
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/postSave", method = RequestMethod.POST)
	@ResponseBody
	public Boolean postSave(BaseOrgInfo orgInfo) {
		Boolean isExist=false;
		isExist=orgInfoManager.save(orgInfo);
		return isExist;
	}
	@RequestMapping(value = "/orgRoleSave", method = RequestMethod.POST)
	@ResponseBody
	public Boolean orgRoleSave(String orgs,String roles) {
		Boolean isExist=false;
		isExist=orgInfoManager.orgRoleSave(orgs,roles);
		return isExist;
	}

	/**
	 * 上移下移
	 * @param id
	 * @param moveType
	 * @return
	 */
	@RequestMapping(value = "/orgTreeMove", method = RequestMethod.GET)
	@ResponseBody
	public Boolean orgTreeMove(String id,String moveType) {
		Boolean isExist=false;
		isExist=orgInfoManager.orgTreeMove(id, moveType);
		return isExist;
	}
	@RequestMapping(value = "/deleteById", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteById(String  PostData) {
		return orgInfoManager.deleteById(PostData);
	}



}
