package com.pmcc.core.web;
import com.pmcc.core.domain.BaseButtons;
import com.pmcc.core.domain.BaseDictionary;
import com.pmcc.core.domain.DictUtil;
import com.pmcc.core.manager.managerfacade.BaseButtonsManagerFacade;
import com.pmcc.core.manager.managerfacade.BaseUtilManagerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("baseUtil")
public class BaseUtilController {

	@Autowired
	BaseUtilManagerFacade utilManager;

	@RequestMapping(value = "/getDictList", method = RequestMethod.GET)
	@ResponseBody
	public List<DictUtil> getDictList(String fileName){
		List<BaseDictionary> list=utilManager.getList(fileName);
		if(list!=null &&list.size()>0){
			List<DictUtil> li=utilManager.getDictByContent(list.get(0).getContent());
			return li;
		}else{
			return null;
		}
	}


}
