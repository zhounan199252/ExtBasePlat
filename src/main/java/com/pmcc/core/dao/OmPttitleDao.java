package com.pmcc.core.dao;

import com.pmcc.base.util.BeanUtils;
import com.pmcc.base.util.ResultBean;
import com.pmcc.core.domain.Dict;
import com.pmcc.core.domain.OmPttitle;
import org.hibernate.Criteria;
import org.hibernate.criterion.*;
import org.hibernate.impl.CriteriaImpl;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${zhounan} on 2016/2/18.
 */
@Repository
public class OmPttitleDao  extends BaseDao<OmPttitle, String>{
    public List<OmPttitle> query(String paprm){
          DetachedCriteria criteria = DetachedCriteria.forClass(OmPttitle.class);

            criteria.add(Restrictions.eq("maNaPttId",paprm));
           criteria.addOrder(Order.asc("pttCode"));
          return (List<OmPttitle>) hibernateTemplate.findByCriteria(criteria);
    }
}
