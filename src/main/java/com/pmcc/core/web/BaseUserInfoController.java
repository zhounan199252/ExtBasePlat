package com.pmcc.core.web;

import com.fasterxml.jackson.annotation.JsonView;
import com.pmcc.core.domain.BaseOrgInfo;
import com.pmcc.core.domain.BaseRoleInfo;
import com.pmcc.core.domain.BaseUserInfo;
import com.pmcc.core.manager.managerfacade.BaseUserInfoManagerFacade;
import com.pmcc.jsonView.BaseView;
import com.pmcc.utils.AppUtils;
import com.pmcc.utils.OnlineUser;
import com.pmcc.utils.SystemPropertyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("baseUserInfo")
public class BaseUserInfoController {

	@Autowired
	BaseUserInfoManagerFacade userInfoManager;

	@RequestMapping(value = "/saveUserRoleRelation", method = RequestMethod.POST)
	@ResponseBody
	public Boolean saveUserRoleRelation(String users,String roles) {
		Boolean isExist=false;
		isExist=userInfoManager.saveUserRoleRelation(users,roles);
		return isExist;
	}
	/**
	 * 登陆验证
	 * @param obj
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>  login(BaseUserInfo obj,HttpServletRequest request){
		List<BaseUserInfo> list=userInfoManager.queryByLogin(obj);
		Map<String, Object> model = new HashMap<String, Object>();
       if(list !=null&& list.size()>0){
		   model.put("result",true);
		   obj=list.get(0);
		   model.put("user",obj);
		   HttpSession session = request.getSession();
		   session.setAttribute("sessionId",session.getId());
		   OnlineUser onlineUser=new OnlineUser();
		   onlineUser.setUserId(obj.getOid());
		   onlineUser.setUserCName(obj.getName());
		   onlineUser.setUserEName(obj.getUserName());
//		   session.setAttribute("userId",obj.getOid());
//		   session.setAttribute("userEName",obj.getName());
//		   session.setAttribute("userCName",obj.getUserName());
		   //需要后期维护，判断使用的组织结构
		   for(BaseOrgInfo orgInfo:obj.getOrgs()){
			   onlineUser.setDepCName(obj.getOrgs().get(0).getName());
			   onlineUser.setDepEName(obj.getOrgs().get(0).getEnglishName());
			   onlineUser.setDepId(obj.getOrgs().get(0).getOid());
//			   session.setAttribute("depCName",obj.getOrgs().get(0).getName());
//			   session.setAttribute("depEName",obj.getOrgs().get(0).getEnglishName());
//			   session.setAttribute("depId",obj.getOrgs().get(0).getOid());
		   }
		   for(BaseRoleInfo roleInfo:obj.getRoles()){
			  // onlineUser.setRoleIds(obj.getRoles());
			   session.setAttribute("roleIds",obj.getRoles());
		   }
		   onlineUser.setIp(AppUtils.getRemortIP(request));
		  // session.setAttribute("ip", AppUtils.getRemortIP(request));
		   //先移除已经存在的。
		   if(AppUtils.getOnlineUser(session.getId())!=null){
			   AppUtils.removeOnlineUser(session.getId());
		   }
		   AppUtils.addOnlineUser(session.getId(),onlineUser);
		   return model;
	   }else{
		   model.put("result",false);
		   return model;
	   }
	}

	/**
	 * 查询所有
	 * @param page 当前页码
	 * @param limit 每页 数量
	 * @param swhere 条件
	 * @param sort 排序
	 * @param type 区分是否分页
     * @param xtype 是否关联查询
     * @return
     */
	@RequestMapping(value = "/getUserList", method = RequestMethod.GET)
	@ResponseBody
	@JsonView(BaseView.UserOrgSummary.class)
	public Map<String, Object> getUserList(int page, int limit,String swhere,String sort,String type,String xtype) {
		Map<String, Object> res = new HashMap<String, Object>();
 		res.put("success",true);
		//type=null 分页，type=ture 不分页
		res.put("total",userInfoManager.getUserList(page,limit,swhere,sort,"true",xtype).size());
		res.put("curPage",page);
		res.put("extData",userInfoManager.getUserList(page,limit,swhere,sort,type,xtype));
		return res;
	}
	/**
	 * 验证用户名称重复
	 */
	@RequestMapping(value = "/getIsExist", method = RequestMethod.GET)
	@ResponseBody
	public Boolean getIsExist(String oid,String col,String val) {
	    Boolean isExist=false;
		if(col.equals("userName")){
			isExist=userInfoManager.isExist(oid,val);
		}
		return isExist;
	}
	/*
	保存用户或者更新用户
	 */
	@RequestMapping(value = "/postSave", method = RequestMethod.POST)
	@ResponseBody
	public Boolean postSave(BaseUserInfo userInfo) {
			Boolean isExist=false;
			isExist=userInfoManager.saveUser(userInfo);
			return isExist;
	}
	@RequestMapping(value = "/deleteById", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteById(String  PostData) {
		return userInfoManager.deleteById(PostData);
	}
	/*
	rsa 加密返回方法，此加密目前未使用
	 */
	@RequestMapping(value = "/getRSAPublicKey", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getRSAPublicKey() {
		Map<String, Object> model = new HashMap<String, Object>();
		String exponent = SystemPropertyUtil.getProperty("exponent");
		String modulus = SystemPropertyUtil.getProperty("modulus");
		model.put("result",true);
		model.put("exponent",exponent);
		model.put("modulus",modulus);
		return model;
	}

//	/*
//	 * 根据id查询
//	 */
//	@RequestMapping(value = "/findById", method = RequestMethod.GET)
//	@ResponseBody
//	public BaseUserInfo findById(BaseUserInfo obj) {
//		BaseUserInfo d = baseUserInfoManager.findById(obj);
//		return d;
//	}
	

}
