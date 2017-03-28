package com.pmcc.core.web;

import com.pmcc.core.domain.PtPassProbationAudit;
import com.pmcc.core.manager.PtEmpreqPlanMainManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhounan on 2016/2/25.
 */
@Controller
@RequestMapping("ptPassProbationAudit")
public class PtPassProbationAuditController   extends BaseAjaxController<PtPassProbationAudit, String>{
   @Autowired
    PtEmpreqPlanMainManager ptEmpreqPlanMainManager;
}




