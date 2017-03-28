package com.pmcc.core.manager.managerfacade;
import com.pmcc.core.domain.BaseUserInfo;
import java.util.List;
import java.util.Map;

public interface BaseUserInfoManagerFacade {
	/**
	 * 入参账号密码，返回登陆人对象
	 * @param obj
	 * @return
	 */
			public List<BaseUserInfo> queryByLogin(BaseUserInfo obj);

			public  List<BaseUserInfo> getUserList(int page, int limit, String swhere, String sort, String type, String xtype);

	/**
	 * 根据主键和用户名判断重复
	 * @param oid
	 * @param val
	 * @return
	 */
		public  Boolean isExist(String oid, String val);

	public Boolean saveUser(BaseUserInfo userInfo);

	public  Map<String,Object> deleteById(String postData);

	Boolean saveUserRoleRelation(String users, String roles);
}
