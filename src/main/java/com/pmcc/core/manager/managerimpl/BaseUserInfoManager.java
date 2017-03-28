package com.pmcc.core.manager.managerimpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pmcc.core.dao.daofacade.BaseUserInfoDaoFacade;
import com.pmcc.core.domain.BaseOrgInfo;
import com.pmcc.core.domain.BaseRoleInfo;
import com.pmcc.core.domain.BaseUserInfo;
import com.pmcc.core.manager.managerfacade.BaseUserInfoManagerFacade;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Repository("userInfoManager")
public class BaseUserInfoManager implements BaseUserInfoManagerFacade {

	@Resource
	private BaseUserInfoDaoFacade baseUserInfoDao;
	/**
	 * 入参账号密码，返回登陆人对象
	 * @param obj
	 * @return
	 */
			@Transactional(readOnly = true)
			public List<BaseUserInfo> queryByLogin(BaseUserInfo obj) {
				List<BaseUserInfo> list= 	baseUserInfoDao.queryByLogin(obj);
				if(list !=null &&list.size()>0){
					for(BaseUserInfo b:list){
						//去除组织机构
						for(BaseOrgInfo orgInfo:b.getOrgs()){
							orgInfo.setModules(null);
							orgInfo.setRoles(null);
						}
						for(BaseRoleInfo roleInfo:b.getRoles()){
							roleInfo.setOrgs(null);
							roleInfo.setPermissions(null);
						}

					}
				}
				return list;
			}


	@Override
			@Transactional(readOnly = true)
			public List<BaseUserInfo> getUserList(int page, int limit, String swhere, String sort, String type, String xtype) {
				List<BaseUserInfo> list=baseUserInfoDao.getUserList(page,limit,swhere,sort,type,xtype);
				if(list !=null &&list.size()>0){
					for(BaseUserInfo b:list){
						//去除组织机构
							for(BaseOrgInfo orgInfo:b.getOrgs()){
								orgInfo.setModules(null);
								orgInfo.setRoles(null);
							}
							for(BaseRoleInfo roleInfo:b.getRoles()){
								roleInfo.setOrgs(null);
								roleInfo.setPermissions(null);
							}

					}
				}
				return  list;
			}

	@Override
	@Transactional(readOnly = true)
	public Boolean isExist(String oid, String val) {
		//根据用户名查询
		BaseUserInfo b=baseUserInfoDao.findByUserName(val);
		if(b !=null){
			if("".equals(oid) || oid==null){
				return true;
			}else if(b.getOid().equals(oid)){
				return  false;
			}else{
				return  true;
			}
		}else{
			return false;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean saveUser(BaseUserInfo userInfo) {
			//新增用户//更新
			userInfo.setCreateTime(new Date());
			return	 baseUserInfoDao.saveOrUpdate(userInfo);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> deleteById(String postData) {
	     //分割postData
		String[] id=postData.split(",");
		int succount=0;
		int errorcount=0;
		for(int i=0;i<id.length;i++){
			if(baseUserInfoDao.deleteById(id[i])){
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

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean saveUserRoleRelation(String users, String roles) {
		return	 baseUserInfoDao.saveUserRoleRelation(users,roles);
	}


}
