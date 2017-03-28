package com.pmcc.core.manager.managerimpl;

import com.pmcc.core.dao.daofacade.BaseButtonsDaoFacade;
import com.pmcc.core.dao.daofacade.BasePermissionInfoDaoFacade;
import com.pmcc.core.dao.daofacade.BaseRoleInfoDaoFacade;
import com.pmcc.core.domain.BaseButtons;
import com.pmcc.core.domain.BasePermissionInfo;
import com.pmcc.core.domain.BaseRoleInfo;
import com.pmcc.core.manager.managerfacade.BaseButtonsManagerFacade;
import com.pmcc.core.manager.managerfacade.BaseRoleInfoManagerFacade;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


@Repository("buttonsManager")
public class BaseButtonsManager implements BaseButtonsManagerFacade {

	@Resource
	private BaseButtonsDaoFacade baseButtonsDao;


			@Override
			@Transactional(readOnly = true)
			public List<BaseButtons> getButtonsList(int page, int limit, String swhere, String sort, String type) {
				return 	baseButtonsDao.getButtonsList(page, limit, swhere, sort, type);
			}

		@Override
		@Transactional(propagation = Propagation.REQUIRED)
		public Boolean save(BaseButtons buttons) {
			buttons.setCreateTime(new Date());
			return baseButtonsDao.saveOrUpdate(buttons);
		}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> deleteById(String postData) {
		//分割postData
		String[] id=postData.split(",");
		int succount=0;
		int errorcount=0;
		for(int i=0;i<id.length;i++){
			if(baseButtonsDao.deleteById(id[i])){
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
