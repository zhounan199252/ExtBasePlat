package com.pmcc.core.dao;

import com.pmcc.core.domain.OmSpecialty;
import org.codehaus.groovy.control.io.ReaderSource;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by H on 2016/2/19.
 */
@Repository
public class OmSpecialtyDao extends BaseDao<OmSpecialty,String> {
    public List<OmSpecialty> query(String omSpecialty){
        DetachedCriteria criteria=DetachedCriteria.forClass(OmSpecialty.class);
        criteria.add(Restrictions.eq("maNaSpecId",omSpecialty));
        return (List<OmSpecialty>)hibernateTemplate.findByCriteria(criteria);
    }
}
