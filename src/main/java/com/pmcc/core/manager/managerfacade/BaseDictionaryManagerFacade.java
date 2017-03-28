package com.pmcc.core.manager.managerfacade;

import com.pmcc.core.domain.BaseButtons;
import com.pmcc.core.domain.BaseDictionary;

import java.util.List;
import java.util.Map;

public interface BaseDictionaryManagerFacade {

			public  List<BaseDictionary> getList(int page, int limit, String swhere, String sort, String type);

			public Boolean save(BaseDictionary dictionary);

			public Map<String,Object> deleteById(String postData);
}
