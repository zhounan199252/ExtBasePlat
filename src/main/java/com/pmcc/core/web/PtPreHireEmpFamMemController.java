package com.pmcc.core.web;

import com.pmcc.core.domain.PtPreHireEmpFamMem;
import com.pmcc.core.manager.PtPreHireEmpFamMemManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Haoco on 2016/2/29.
 */
@Controller
@RequestMapping("ptPreHiReEmpFamMem")
public class PtPreHireEmpFamMemController extends BaseAjaxController<PtPreHireEmpFamMem,String>{
    @Autowired
    PtPreHireEmpFamMemManager ptPreHireEmpFamMemManager;
}
