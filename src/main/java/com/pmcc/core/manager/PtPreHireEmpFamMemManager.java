package com.pmcc.core.manager;

import com.pmcc.core.dao.PtPreHireEmpFamMemDao;
import com.pmcc.core.domain.PtPreHireEmpFamMem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Haoco on 2016/2/29.
 */
@Transactional
@Repository
public class PtPreHireEmpFamMemManager extends BaseManager<PtPreHireEmpFamMem,String>{
    @Autowired
    PtPreHireEmpFamMemDao ptPreHireEmpFamMemDao;
}
