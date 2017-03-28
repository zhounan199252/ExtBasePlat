package com.pmcc.core.dao.daoimpl;

import com.pmcc.core.dao.daofacade.BaseUserInfoDaoFacade;
import com.pmcc.core.domain.BaseOrgInfo;
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
@Repository("baseUserInfoDao")
public class BaseUserInfoDao  extends HibernateDaoSupport implements BaseUserInfoDaoFacade {
    @Resource
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    public List<BaseUserInfo> queryByLogin(BaseUserInfo obj) {
        DetachedCriteria criteria=DetachedCriteria.forClass(BaseUserInfo.class);
        criteria.add(Restrictions.and(Restrictions.eq("userName", obj.getUserName()), Restrictions.eq("userPwd", obj.getUserPwd())));
         return (List<BaseUserInfo>) this.getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<BaseUserInfo> getUserList(int page, int limit, String swhere, String sort, String type, String xtype) {
        DetachedCriteria criteria=DetachedCriteria.forClass(BaseUserInfo.class);
        if(!swhere.equals("")){
            String[] rwhere=swhere.split("<<");
            for(String r:rwhere){
                String[] where=r.split("\\|");
                if(where[1].equals("string")){
                    if("org".equals(xtype)){
                        criteria.createAlias("orgs","org").add(Restrictions.eq("org.oid",where[2]));
                    }else{
                        criteria.add(Restrictions.like(where[0],"%"+where[2]+"%"));
                    }
                }else if(where[1].equals("int")){
                    criteria.add(Restrictions.eq(where[0],where[2]));
                }else {
                    //日期类型暂时没想好
                }
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
          return   (List<BaseUserInfo>) this.getHibernateTemplate().findByCriteria(criteria);
        }else{
            //分页
           return  (List<BaseUserInfo>) this.getHibernateTemplate().findByCriteria(criteria,(page-1)*limit,limit);
        }


    }

    @Override
    public BaseUserInfo findByUserName(String val) {
        DetachedCriteria criteria=DetachedCriteria.forClass(BaseUserInfo.class);
        criteria.add(Restrictions.eq("userName",val));
       List<BaseUserInfo> list= (List<BaseUserInfo>) this.getHibernateTemplate().findByCriteria(criteria);
        if(list !=null && list.size()>0){
            return  list.get(0);
        }else{
            return null;
        }
    }

    @Override
    /**
     * 新增方法
     */
    public Boolean saveOrUpdate(BaseUserInfo userInfo) {
       // this.getHibernateTemplate().save(userInfo);
        String[] str=userInfo.getOrgsId().split(",");
        List<BaseOrgInfo> list=new ArrayList<BaseOrgInfo>(str.length);
        for(int i=0;i<str.length;i++){
            // BaseRelationUserOrg bruo=new BaseRelationUserOrg(UUIDGenerator.getUUID(),userInfo.getOid(),str[i]);
            list.add((BaseOrgInfo)this.getHibernateTemplate().get(BaseOrgInfo.class,str[i]));
        }
           userInfo.setOrgs(list);
        try {
            this.getHibernateTemplate().saveOrUpdate(userInfo);
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
            this.getHibernateTemplate().delete(new BaseUserInfo(id));
            this.getHibernateTemplate().flush();
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean saveUserRoleRelation(String users, String roles) {
        String[] user=users.split(",");
        for(String u:user){
            BaseUserInfo userInfo= this.getHibernateTemplate().get(BaseUserInfo.class, u);
            if(!"".equals(roles)&& roles!=null){
                String[] role=roles.split(",");
                List<BaseRoleInfo> list=new ArrayList<BaseRoleInfo>();
                for(String r:role){
                    list.add(this.getHibernateTemplate().get(BaseRoleInfo.class,r));
                }
                userInfo.setRoles(list);
            }else{
                userInfo.setRoles(null);
            }
            try {
                this.getHibernateTemplate().saveOrUpdate(userInfo);
                this.getHibernateTemplate().flush();
            } catch (DataAccessException e) {
                e.printStackTrace();
                return false;
            }
        }
        return  true;
    }


}
