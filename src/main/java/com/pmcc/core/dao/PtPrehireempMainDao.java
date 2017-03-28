package com.pmcc.core.dao;

import com.pmcc.core.domain.PtPrehireempMain;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by {zhangsongwei} on 2016/2/24.
 */
@Repository
public class PtPrehireempMainDao extends BaseDao<PtPrehireempMain, String>{

    public List<PtPrehireempMain> query(String paprm){
        DetachedCriteria criteria = DetachedCriteria.forClass(PtPrehireempMain.class);
        criteria.add(Restrictions.eq("manaworktypeId",paprm));
        return (List<PtPrehireempMain>) hibernateTemplate.findByCriteria(criteria);
    }

}
