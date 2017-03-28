package com.pmcc.core.manager;

import com.pmcc.core.dao.OmSpecialtyDao;
import com.pmcc.core.domain.OmSpecialty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by H on 2016/2/19.
 */
@Transactional
@Repository
public class OmSpecialtyManager extends BaseManager<OmSpecialty,String> {
    @Autowired
    OmSpecialtyDao omSpecialtyDao;

    public List<OmSpecialty> query(String omSpecialty){
        return omSpecialtyDao.query(omSpecialty);
    }
}
