package com.pmcc.core.manager.managerimpl;

import com.pmcc.core.dao.daofacade.BaseButtonsDaoFacade;
import com.pmcc.core.dao.daofacade.BaseDictionaryDaoFacade;
import com.pmcc.core.domain.BaseButtons;
import com.pmcc.core.domain.BaseDictionary;
import com.pmcc.core.manager.managerfacade.BaseButtonsManagerFacade;
import com.pmcc.core.manager.managerfacade.BaseDictionaryManagerFacade;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository("dictionaryManager")
public class BaseDictionaryManager implements BaseDictionaryManagerFacade {

	@Resource
	private BaseDictionaryDaoFacade baseDictionaryDao;


			@Override
			@Transactional(readOnly = true)
			public List<BaseDictionary> getList(int page, int limit, String swhere, String sort, String type) {
				return 	baseDictionaryDao.getList(page, limit, swhere, sort, type);
			}

		@Override
		@Transactional(propagation = Propagation.REQUIRED)
		public Boolean save(BaseDictionary dictionary) {
			return baseDictionaryDao.saveOrUpdate(dictionary);
		}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> deleteById(String postData) {
		//分割postData
		String[] id=postData.split(",");
		int succount=0;
		int errorcount=0;
		for(int i=0;i<id.length;i++){
			if(baseDictionaryDao.deleteById(id[i])){
				succount++;
			}else {
				errorcount++;
			};
		}
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("result",true);
		res.put("message","处理成功！");
		res.put("succount",succount);
		res.put("errorcount",errorcount);
		return res;
	}



}
