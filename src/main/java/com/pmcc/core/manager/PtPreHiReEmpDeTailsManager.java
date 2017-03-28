package com.pmcc.core.manager;

import com.pmcc.core.dao.PtPreHiReEmpDeTailsDao;
import com.pmcc.core.domain.PtPreHiReEmpDeTails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by H on 2016/2/25.
 */
@Transactional
@Repository
public class PtPreHiReEmpDeTailsManager extends BaseManager<PtPreHiReEmpDeTails,String>{
    @Autowired
    PtPreHiReEmpDeTailsDao ptPreHiReEmpDeTailsDao;
}
