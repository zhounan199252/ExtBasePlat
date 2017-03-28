package com.pmcc.core.manager;

import com.pmcc.core.dao.PtPrehireempMainDao;
import com.pmcc.core.domain.PtPrehireempMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhangsongwei on 2016/2/24.
 */
@Transactional
@Repository
public class PtPrehireempMainManager extends BaseManager<PtPrehireempMain,String> {

    @Autowired
    PtPrehireempMainDao ptPrehireempMainDao;

    public List<PtPrehireempMain> query(String paprm){

        return  ptPrehireempMainDao.query(paprm);
    }
}
