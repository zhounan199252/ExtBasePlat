package com.pmcc.core.manager;

import com.pmcc.core.dao.PtEmpreqPlanMainDao;
import com.pmcc.core.domain.PtEmpreqPlanMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhounan on 2016/2/23.
 */
@Transactional
@Repository
public class PtEmpreqPlanMainManager  extends BaseManager<PtEmpreqPlanMain, String> {
    @Autowired
    PtEmpreqPlanMainDao  ptEmpreqPlanMainDao;
}
