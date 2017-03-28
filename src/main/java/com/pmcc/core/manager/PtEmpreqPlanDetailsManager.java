package com.pmcc.core.manager;

import com.pmcc.core.dao.PtEmpreqPlanDetailsDao;
import com.pmcc.core.domain.PtEmpreqPlanDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhounan on 2016/2/29.
 */
@Repository
@Transactional
public class PtEmpreqPlanDetailsManager  extends BaseManager<PtEmpreqPlanDetail,String> {
@Autowired
    PtEmpreqPlanDetailsDao   ptEmpreqPlanDetailsDao;


}

