package com.pmcc.core.web;

import com.pmcc.core.domain.PtPreHiReEmpDeTails;
import com.pmcc.core.manager.PtPreHiReEmpDeTailsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by H on 2016/2/25.
 */
@Controller
@RequestMapping("ptPreHiReEmpDeTails")
public class PtPreHiReEmpDeTailsController extends BaseAjaxController<PtPreHiReEmpDeTails,String>{
    @Autowired
    PtPreHiReEmpDeTailsManager ptPreHiReEmpDeTailsManager;
}
