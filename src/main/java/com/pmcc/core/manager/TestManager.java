package com.pmcc.core.manager;

import com.pmcc.core.dao.BaseDao;
import com.pmcc.core.dao.TestDao;
import com.pmcc.core.domain.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Admin on 2016/1/25.
 */
@Transactional
@Repository("testManager")
public class TestManager extends BaseManager<Test, String>{

    @Autowired
    TestDao testDao;


}
