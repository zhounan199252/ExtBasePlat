package com.pmcc.core.dao.daoimpl;

import com.pmcc.core.dao.daofacade.BaseModuleInfoDaoFacade;
import com.pmcc.core.dao.daofacade.BaseOrgInfoDaoFacade;
import com.pmcc.core.domain.BaseModuleInfo;
import com.pmcc.core.domain.BaseOrgInfo;
import com.pmcc.core.domain.BaseRoleInfo;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
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

@Component
@Repository("baseOrgInfoDao")
public class BaseOrgInfoDao extends HibernateDaoSupport implements BaseOrgInfoDaoFacade {
    @Resource
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public List<BaseOrgInfo> getOrgList(String pid) {
        return null;
    }

    @Override
    public List<BaseOrgInfo> getOrgList() {
        DetachedCriteria criteria=DetachedCriteria.forClass(BaseOrgInfo.class);
        criteria.add(Restrictions.eq("state", 0));
        criteria.addOrder(Order.asc("LT"));
       return (List<BaseOrgInfo>) this.getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<BaseOrgInfo> getOrgList(String orgId, String type) {
        return null;
    }

    @Override
    public List<BaseOrgInfo> getOrgList(int page, int limit, String swhere, String sort) {
        return null;
    }

    @Override
    public List<BaseOrgInfo> query(int treelevel, String pid) {
        DetachedCriteria criteria=DetachedCriteria.forClass(BaseOrgInfo.class);
        if(pid!=null){
            criteria.add(Restrictions.eq("parentOid",pid));
        }
        criteria.add(Restrictions.eq("treeLevel",treelevel));
        criteria.addOrder(Order.asc("LT"));
        return (List<BaseOrgInfo>) this.getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public Boolean saveOrUpdate(BaseOrgInfo orgInfo) {
        try {
            //新增
            if(orgInfo.getOid()==null ||"".equals(orgInfo.getOid())){
                String sql="{CALL TreeNodeAdd(?,?,?,?)}";
                this.getHibernateTemplate().saveOrUpdate(orgInfo);
                this.getHibernateTemplate().flush();
                SQLQuery query= this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
                query.setString(0,"BASE_ORGINFO");
                query.setString(1,"oid");
                query.setString(2,orgInfo.getOid());
                query.setString(3,orgInfo.getParentOid());
                query.executeUpdate();
            }else{
                //修改
                BaseOrgInfo b=new BaseOrgInfo();
                b=this.getHibernateTemplate().get(BaseOrgInfo.class,orgInfo.getOid());
                String sql="{CALL TreeNodeUpdate(?,?,?,?)}";
                orgInfo.setLT(b.getLT());
                orgInfo.setRT(b.getRT());
                this.getHibernateTemplate().clear();
                this.getHibernateTemplate().saveOrUpdate(orgInfo);
                this.getHibernateTemplate().flush();
                SQLQuery query= this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
                query.setString(0,"BASE_ORGINFO");
                query.setString(1,"oid");
                query.setString(2,orgInfo.getOid());
                query.setString(3,b.getParentOid());
                query.executeUpdate();
            }
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return  false;
        }
    }

    @Override
    public Boolean orgTreeMove(String id, String moveType) {
        String sql="{CALL TreeNodeMove(?,?,?,?)}";
        SQLQuery query= this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
        query.setString(0,"BASE_ORGINFO");
        query.setString(1,"oid");
        query.setString(2,id);
        if(moveType.equals("up")){
            query.setInteger(3,-1);
        }else {
            query.setInteger(3,1);
        }
        int i=query.executeUpdate();
        if(i>0){
            return  true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteById(String id) {
        try {
            //先通过过程处理数据
            String sql="{CALL TreeNodeDelete(?,?,?)}";
            SQLQuery query= this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
            query.setString(0,"BASE_ORGINFO");
            query.setString(1,"oid");
            query.setString(2,id);
            int i=query.executeUpdate();
//            if(i>0){
                //成功后删除该对象
                sql="delete  from  BASE_ORGINFO where parentoid=-1 ";
                query  =  this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
                query.executeUpdate();
            this.getSessionFactory().getCurrentSession().flush();
                return true;
//            }else{
//                return  false;
//            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public BaseOrgInfo find(String orgId) {
        BaseOrgInfo baseOrgInfo=(BaseOrgInfo)this.getHibernateTemplate().get(BaseOrgInfo.class,orgId);
        baseOrgInfo.setModules(null);
        return baseOrgInfo;
    }



    public BaseOrgInfo findBoi(String orgId) {
        BaseOrgInfo baseOrgInfo=(BaseOrgInfo)this.getHibernateTemplate().get(BaseOrgInfo.class,orgId);
        return baseOrgInfo;
    }

    @Override
    public BaseOrgInfo getOrgInfo(String orgId) {
        BaseOrgInfo baseOrgInfo=(BaseOrgInfo)this.getHibernateTemplate().get(BaseOrgInfo.class,orgId);
        return baseOrgInfo;
    }

    @Override
    public Boolean orgRoleSave(String orgs, String roles) {
        BaseOrgInfo baseOrgInfo=(BaseOrgInfo)this.getHibernateTemplate().get(BaseOrgInfo.class,orgs);
        List<BaseRoleInfo> roleInfos=new ArrayList<BaseRoleInfo>();
        if(roles!=null && !"".equals(roles)){
            String[] role=roles.split(",");
            for(String s:role){
                roleInfos.add((BaseRoleInfo)this.getHibernateTemplate().get(BaseRoleInfo.class,s));
            }
        }
        baseOrgInfo.setRoles(roleInfos);
        try {
            this.getHibernateTemplate().saveOrUpdate(baseOrgInfo);
            this.getHibernateTemplate().flush();
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }


}
