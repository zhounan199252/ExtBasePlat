package com.pmcc.core.web;
import com.pmcc.core.domain.BaseButtons;
import com.pmcc.core.domain.BaseRoleInfo;
import com.pmcc.core.manager.managerfacade.BaseButtonsManagerFacade;
import com.pmcc.core.manager.managerfacade.BaseRoleInfoManagerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("buttons")
public class BaseButtonsController {

	@Autowired
	BaseButtonsManagerFacade buttonsManager;

	@RequestMapping(value = "/getButtonsList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getButtonsList(int page, int limit,String swhere,String sort,String type){
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("success",true);
		//type=null 分页，type=ture 不分页
		res.put("total",buttonsManager.getButtonsList(page, limit, swhere, sort, "true").size());
		res.put("curPage",page);
		res.put("extData",buttonsManager.getButtonsList(page, limit, swhere, sort, type));
		return res;
	}
	@RequestMapping(value = "/postSave", method = RequestMethod.POST)
	@ResponseBody
	public Boolean postSave(BaseButtons baseButtons) {
		Boolean isExist=false;
		isExist=buttonsManager.save(baseButtons);
		return isExist;
	}
	@RequestMapping(value = "/deleteById", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteById(String  PostData) {
		return buttonsManager.deleteById(PostData);
	}

	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	@ResponseBody
	public List<BaseButtons> getList(int page, int limit,String type){

		//type=null 分页，type=ture 不分页
		return buttonsManager.getButtonsList(page, limit, null,null, "true");
	}

}
