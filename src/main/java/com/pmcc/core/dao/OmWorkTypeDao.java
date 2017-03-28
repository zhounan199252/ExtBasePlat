package com.pmcc.core.dao;
import com.pmcc.core.domain.OmWorkType;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by ${zhangsongwei} on 2016/2/19.
 */
@Repository
public class OmWorkTypeDao extends BaseDao<OmWorkType, String>{
    public List<OmWorkType> query(String paprm){
        DetachedCriteria criteria = DetachedCriteria.forClass(OmWorkType.class);
        criteria.add(Restrictions.eq("manaworktypeId",paprm));
        return (List<OmWorkType>) hibernateTemplate.findByCriteria(criteria);
    }
}

