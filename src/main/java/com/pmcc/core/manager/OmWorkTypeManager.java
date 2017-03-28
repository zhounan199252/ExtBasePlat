package com.pmcc.core.manager;



import com.pmcc.core.dao.OmWorkTypeDao;
import com.pmcc.core.domain.OmWorkType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ${zhangsongwei} on 2016/2/19.
 */
@Transactional
@Repository
public class OmWorkTypeManager extends BaseManager<OmWorkType, String> {
    @Autowired
    OmWorkTypeDao workTypeDao;

    public List<OmWorkType> query(String paprm){

        return    workTypeDao.query(paprm);
    }
}

