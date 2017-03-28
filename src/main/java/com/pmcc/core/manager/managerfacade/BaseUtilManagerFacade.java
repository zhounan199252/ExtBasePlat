package com.pmcc.core.manager.managerfacade;

import com.pmcc.core.domain.BaseButtons;
import com.pmcc.core.domain.BaseDictionary;
import com.pmcc.core.domain.DictUtil;

import java.util.List;
import java.util.Map;

public interface BaseUtilManagerFacade {

			public  List<BaseDictionary> getList(String fileName);

			List<DictUtil> getDictByContent(String content);
}
