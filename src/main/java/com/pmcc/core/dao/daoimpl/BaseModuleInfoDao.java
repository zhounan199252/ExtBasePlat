package com.pmcc.core.dao.daoimpl;

import com.pmcc.core.dao.daofacade.BaseModuleInfoDaoFacade;
import com.pmcc.core.domain.*;
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
@Repository("baseModuleInfoDao")
public class BaseModuleInfoDao extends HibernateDaoSupport implements BaseModuleInfoDaoFacade  {
    @Resource
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }


    @Override
    public List<BaseModuleInfo> queryUserModule(String userId, String orgId) {
        String sql="SELECT * from BASE_MODULEINFO a where a.FLAG=0  AND a.OID in (" +
                "SELECT d.MODULEID FROM BASE_RELATION_USERROLE b,BASE_RELATION_ROLEPERMISSION c,BASE_RELATION_PERMISSIONMODULE d,BASE_RELATION_ORGMODULE e " +
                "where b.USERID='"+userId+"' AND b.ROLEID=c.ROLEID AND c.PERMISSIONID=d.PERMISSIONID AND e.ORGID='"+orgId+"' AND e.MODULEID=d.MODULEID" +
                ") ORDER BY a.LT;";
        return (List<BaseModuleInfo>) this.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(BaseModuleInfo.class).list();
    }

    @Override
    public List<BaseModuleInfo> queryUserButtons(String moduleEname) {
        //moduleEname查询出单条数据
        DetachedCriteria criteria=DetachedCriteria.forClass(BaseModuleInfo.class);
        criteria.add(Restrictions.eq("ename",moduleEname));
       List<BaseModuleInfo> flist= (List<BaseModuleInfo>) this.getHibernateTemplate().findByCriteria(criteria);
        //查询出list
        DetachedCriteria c=DetachedCriteria.forClass(BaseModuleInfo.class);
        c.add(Restrictions.eq("parentOid",flist.get(0).getOid()));
        Integer[] flag={1,3,4};
        c.add(Restrictions.in("flag",flag));
        c.addOrder(Order.asc("LT"));
        List<BaseModuleInfo> list= (List<BaseModuleInfo>) this.getHibernateTemplate().findByCriteria(c);
       return  list;
    }

    @Override
    public List<BaseModuleInfo> queryModuleByPid(String pid) {
        DetachedCriteria criteria=DetachedCriteria.forClass(BaseModuleInfo.class);
        DetachedCriteria criteriaList=DetachedCriteria.forClass(BaseModuleInfo.class);
        criteriaList.add(Restrictions.and(Restrictions.eq("flag",0),Restrictions.eq("parentOid",pid)));
        List<BaseModuleInfo> listModule= (List<BaseModuleInfo>) this.getHibernateTemplate().findByCriteria(criteriaList);
        List<String> listString=new ArrayList<String>();
        for(int i=0;i<listModule.size();i++){
            listString.add(listModule.get(i).getOid());
        }
        criteria.add(Restrictions.or(Restrictions.in("parentOid",listString),Restrictions.eq("parentOid",pid)));
        criteria.add(Restrictions.eq("flag",0));
        criteria.addOrder(Order.asc("LT"));
        return (List<BaseModuleInfo>) this.getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<BaseModuleInfo> queryModule(int treelevel,String pid) {
        DetachedCriteria criteria=DetachedCriteria.forClass(BaseModuleInfo.class);
        if(pid!=null){
           criteria.add(Restrictions.eq("parentOid",pid));
        }
        criteria.add(Restrictions.eq("treeLevel",treelevel));
        criteria.addOrder(Order.asc("LT"));
        return (List<BaseModuleInfo>) this.getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<BasePermissionInfo> getModulePermissionByOid(String oid) {
        String sql="SELECT * from BASE_PERMISSIONINFO a where a.oid in (SELECT b.PERMISSIONID from BASE_RELATION_PERMISSIONMODULE b where b.MODULEID='"+oid+"')";
        return (List<BasePermissionInfo>) this.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(BasePermissionInfo.class).list();
    }

    @Override
    public BaseModuleInfo findByEname(String val) {
        DetachedCriteria criteria=DetachedCriteria.forClass(BaseModuleInfo.class);
        criteria.add(Restrictions.eq("ename",val));
        List<BaseModuleInfo> list= (List<BaseModuleInfo>) this.getHibernateTemplate().findByCriteria(criteria);
        if(list !=null && list.size()>0){
            return  list.get(0);
        }else{
            return null;
        }
    }

    @Override
    public Boolean saveOrUpdate(BaseModuleInfo moduleInfo) {
        String[] str=moduleInfo.getPermissionsId().split(",");
        List<BasePermissionInfo> list=new ArrayList<BasePermissionInfo>(str.length);
        for(int i=0;i<str.length;i++){
            list.add((BasePermissionInfo) this.getHibernateTemplate().get(BasePermissionInfo.class, str[i]));
        }
        moduleInfo.setPermissions(list);
        try {
            //新增
            if(moduleInfo.getOid()==null ||"".equals(moduleInfo.getOid())){
                String sql="{CALL TreeNodeAdd(?,?,?,?)}";
                this.getHibernateTemplate().saveOrUpdate(moduleInfo);
                this.getHibernateTemplate().flush();
                SQLQuery query= this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
                query.setString(0,"BASE_MODULEINFO");
                query.setString(1,"oid");
                query.setString(2,moduleInfo.getOid());
                query.setString(3,moduleInfo.getParentOid());
                query.executeUpdate();
            }else{
                //修改
                BaseModuleInfo b=new BaseModuleInfo();
                b=this.getHibernateTemplate().get(BaseModuleInfo.class,moduleInfo.getOid());
                String sql="{CALL TreeNodeUpdate(?,?,?,?)}";
                moduleInfo.setLT(b.getLT());
                moduleInfo.setRT(b.getRT());
                this.getHibernateTemplate().clear();
                this.getHibernateTemplate().saveOrUpdate(moduleInfo);
                this.getHibernateTemplate().flush();
                SQLQuery query= this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
                query.setString(0,"BASE_MODULEINFO");
                query.setString(1,"oid");
                query.setString(2,moduleInfo.getOid());
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
    public Boolean moduleTreeMove(String oid, String moveType) {
        String sql="{CALL TreeNodeMove(?,?,?,?)}";
        SQLQuery query= this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
        query.setString(0,"BASE_MODULEINFO");
        query.setString(1,"oid");
        query.setString(2,oid);
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
            query.setString(0,"BASE_MODULEINFO");
            query.setString(1,"oid");
            query.setString(2,id);
            int i=query.executeUpdate();
            if(i>0){
               //成功后删除该对象
              BaseModuleInfo b= (BaseModuleInfo) this.getSessionFactory().getCurrentSession().get(BaseModuleInfo.class,id);
                this.getSessionFactory().getCurrentSession().delete(b);
                this.getSessionFactory().getCurrentSession().flush();
                return true;
            }else{
                return  false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public BaseModuleInfo find(String id) {
        return this.getHibernateTemplate().get(BaseModuleInfo.class,id);
    }

    @Override
    public void saveOrUpdatePermission(BaseModuleInfo baseModuleInfo) {
        this.getHibernateTemplate().saveOrUpdate(baseModuleInfo);
        this.getHibernateTemplate().flush();
    }

    @Override
    public boolean isOrgRelaModule(String oid, String orgId) {
        DetachedCriteria criteria=DetachedCriteria.forClass(BaseRelationOrgModule.class);
        criteria.add(Restrictions.eq("orgId",orgId));
        criteria.add(Restrictions.eq("moduleId",oid));
       List<BaseRelationOrgModule> list= (List<BaseRelationOrgModule>) this.getHibernateTemplate().findByCriteria(criteria);
       if(list!=null &&list.size()>0){
           return true;
       }else {
           return false;
       }
    }

    @Override
    public Boolean updateRelaModuleOrg(String orgId, String moduleIds) {
        List<BaseModuleInfo> list=new ArrayList<BaseModuleInfo>();
        if(moduleIds !=null&& !"".equals(moduleIds)){
            String[] str=moduleIds.split(",");
            for(String s:str){
                list.add(this.getHibernateTemplate().get(BaseModuleInfo.class,s));
            }
        }
      BaseOrgInfo orgInfo=  this.getHibernateTemplate().get(BaseOrgInfo.class, orgId);

        orgInfo.setModules(list);
        try {
            this.getHibernateTemplate().saveOrUpdate(orgInfo);
            this.getHibernateTemplate().flush();
            return  true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public List<BaseModuleInfo> getModuleList() {
        DetachedCriteria criteria=DetachedCriteria.forClass(BaseOrgInfo.class);
        criteria.add(Restrictions.eq("flag", 0));
        criteria.addOrder(Order.asc("LT"));
        return (List<BaseModuleInfo>) this.getHibernateTemplate().findByCriteria(criteria);
    }
    @Override
    public List<BaseModuleInfo> query(int treelevel, String pid,String flag) {
        DetachedCriteria criteria=DetachedCriteria.forClass(BaseModuleInfo.class);
        if(pid!=null){
            criteria.add(Restrictions.eq("parentOid",pid));
        }
        if(!"1".equals(flag)) {
            criteria.add(Restrictions.eq("flag", 0));
        }
        criteria.add(Restrictions.eq("treeLevel",treelevel));
        criteria.addOrder(Order.asc("LT"));
        return (List<BaseModuleInfo>) this.getHibernateTemplate().findByCriteria(criteria);
    }

}
