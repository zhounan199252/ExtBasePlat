package com.pmcc.core.dao.daoimpl;

import com.pmcc.core.dao.daofacade.BaseRoleInfoDaoFacade;
import com.pmcc.core.dao.daofacade.BaseUserInfoDaoFacade;
import com.pmcc.core.domain.BaseOrgInfo;
import com.pmcc.core.domain.BasePermissionInfo;
import com.pmcc.core.domain.BaseRoleInfo;
import com.pmcc.core.domain.BaseUserInfo;
import net.sf.json.JSONArray;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Repository("baseRoleInfoDao")
public class BaseRoleInfoDao extends HibernateDaoSupport implements BaseRoleInfoDaoFacade {
    @Resource
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public List<BaseRoleInfo> getRoleList(int page, int limit, String swhere, String sort, String type, String xtype) {
       DetachedCriteria criteria=DetachedCriteria.forClass(BaseRoleInfo.class);
        if(!swhere.equals("")) {
            String[] where = swhere.split("\\|");
            criteria.createAlias("orgs","orgs").add(Restrictions.eq("orgs.oid", where[2]));
        }
        JSONArray json=JSONArray.fromObject(sort);
        List<Map<String,Object>>  maplist=(List)json;
        if(maplist.get(0).get("direction").equals("DESC")){
            criteria.addOrder(Order.desc(maplist.get(0).get("property").toString()));
        }else{
            criteria.addOrder(Order.asc(maplist.get(0).get("property").toString()));
        }
        if("true".equals(type)){
            //不分页
            return   (List<BaseRoleInfo>) this.getHibernateTemplate().findByCriteria(criteria);
        }else{
            //分页
            return (List<BaseRoleInfo>) this.getHibernateTemplate().findByCriteria(criteria,(page-1)*limit,limit);
        }
    }
    @Override
    public List<BaseRoleInfo> getRoleList(int page, int limit, String swhere, String sort, String type) {
        DetachedCriteria criteria=DetachedCriteria.forClass(BaseRoleInfo.class);
        if(!swhere.equals("")){
            String[] where=swhere.split("\\|");
            if(where[1].equals("string")){
                    criteria.add(Restrictions.like(where[0],"%"+where[2]+"%"));
            }else if(where[1].equals("int")){
                criteria.add(Restrictions.eq(where[0],where[2]));
            }else {
                //日期类型暂时没想好
            }

        }
        JSONArray json=JSONArray.fromObject(sort);
        List<Map<String,Object>>  maplist=(List)json;
        if(maplist.get(0).get("direction").equals("DESC")){
           criteria.addOrder(Order.desc(maplist.get(0).get("property").toString()));
        }else{
            criteria.addOrder(Order.asc(maplist.get(0).get("property").toString()));
        }
        if("true".equals(type)){
           //不分页
          return   (List<BaseRoleInfo>) this.getHibernateTemplate().findByCriteria(criteria);
        }else{
            //分页
          return (List<BaseRoleInfo>) this.getHibernateTemplate().findByCriteria(criteria,(page-1)*limit,limit);
        }


    }

    @Override
    public Boolean saveOrUpdate(BaseRoleInfo roleInfo) {
        String[] str=roleInfo.getPermissionsId().split(",");
        List<BasePermissionInfo> list=new ArrayList<BasePermissionInfo>(str.length);
        for(int i=0;i<str.length;i++){
            list.add((BasePermissionInfo)this.getHibernateTemplate().get(BasePermissionInfo.class,str[i]));
        }
        roleInfo.setPermissions(list);
        try {
            this.getHibernateTemplate().saveOrUpdate(roleInfo);
            this.getHibernateTemplate().flush();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return  false;
        }
    }

    @Override
    public boolean deleteById(String id) {
        try {
            this.getHibernateTemplate().delete(new BaseRoleInfo(id));
            this.getHibernateTemplate().flush();
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public BaseRoleInfo find(String id) {
        return this.getHibernateTemplate().get(BaseRoleInfo.class,id);
    }

    @Override
    public void saveOrUpdatePermission(BaseRoleInfo baseRoleInfo) {
        this.getHibernateTemplate().saveOrUpdate(baseRoleInfo);
        this.getHibernateTemplate().flush();
    }




}
