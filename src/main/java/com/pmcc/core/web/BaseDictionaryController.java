package com.pmcc.core.web;
import com.pmcc.core.domain.BaseButtons;
import com.pmcc.core.domain.BaseDictionary;
import com.pmcc.core.manager.managerfacade.BaseButtonsManagerFacade;
import com.pmcc.core.manager.managerfacade.BaseDictionaryManagerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("dictionary")
public class BaseDictionaryController {

	@Autowired
	BaseDictionaryManagerFacade dictionaryManager;

	@RequestMapping(value = "/getDictList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getDictList(int page, int limit,String swhere,String sort,String type){
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("success",true);
		//type=null 分页，type=ture 不分页
		res.put("total",dictionaryManager.getList(page, limit, swhere, sort, "true").size());
		res.put("curPage",page);
		res.put("extData",dictionaryManager.getList(page, limit, swhere, sort, type));
		return res;
	}
	@RequestMapping(value = "/postSave", method = RequestMethod.POST)
	@ResponseBody
	public Boolean postSave(BaseDictionary dictionary) {
		Boolean isExist=false;
		isExist=dictionaryManager.save(dictionary);
		return isExist;
	}
	@RequestMapping(value = "/deleteById", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteById(String  ids) {
		return dictionaryManager.deleteById(ids);
	}


}
