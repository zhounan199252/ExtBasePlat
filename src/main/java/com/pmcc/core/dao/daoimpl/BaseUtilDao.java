package com.pmcc.core.dao.daoimpl;

import com.pmcc.core.dao.daofacade.BaseButtonsDaoFacade;
import com.pmcc.core.dao.daofacade.BaseUtilDaoFacade;
import com.pmcc.core.domain.BaseButtons;
import com.pmcc.core.domain.BaseDictionary;
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
import java.util.List;
import java.util.Map;

@Component
@Repository("baseUtilDao")
public class BaseUtilDao extends HibernateDaoSupport implements BaseUtilDaoFacade {
    @Resource
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public List<BaseDictionary> getList(String fileName) {
        DetachedCriteria criteria=DetachedCriteria.forClass(BaseDictionary.class);
        criteria.add(Restrictions.eq("filename",fileName));
        return   (List<BaseDictionary>) this.getHibernateTemplate().findByCriteria(criteria);
    }


}
