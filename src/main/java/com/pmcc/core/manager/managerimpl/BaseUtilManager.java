package com.pmcc.core.manager.managerimpl;

import com.pmcc.core.dao.daofacade.BaseButtonsDaoFacade;
import com.pmcc.core.dao.daofacade.BaseUtilDaoFacade;
import com.pmcc.core.domain.BaseButtons;
import com.pmcc.core.domain.BaseDictionary;
import com.pmcc.core.domain.DictUtil;
import com.pmcc.core.manager.managerfacade.BaseButtonsManagerFacade;
import com.pmcc.core.manager.managerfacade.BaseUtilManagerFacade;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


@Repository("utilManager")
public class BaseUtilManager implements BaseUtilManagerFacade {
	@Resource
	private BaseUtilDaoFacade baseUtilDao;
			@Override
			@Transactional(readOnly = true)
			public List<BaseDictionary> getList( String fileName) {
				return 	baseUtilDao.getList(fileName);
			}

	@Override
	public List<DictUtil> getDictByContent(String content) {
		List<DictUtil> list=new ArrayList<DictUtil>();
		String[] strings=content.split(",");
		for(int i=0;i<strings.length;i++){
			String[] s2=strings[i].split("\\|");
			DictUtil dictUtil=new DictUtil();
			dictUtil.setText(s2[0]);
			dictUtil.setValue(s2[1]);
			list.add(dictUtil);
		}
		return list;
	}

}
