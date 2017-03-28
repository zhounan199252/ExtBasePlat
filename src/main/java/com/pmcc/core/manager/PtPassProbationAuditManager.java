package com.pmcc.core.manager;

import com.pmcc.core.dao.PtPassProbationAuditDao;
import com.pmcc.core.domain.PtPassProbationAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhounan on 2016/2/25.
 */
@Transactional
@Repository
public class PtPassProbationAuditManager   extends BaseManager<PtPassProbationAudit,String>{
    @Autowired
    PtPassProbationAuditDao ptPassProbationAuditDao;
}
