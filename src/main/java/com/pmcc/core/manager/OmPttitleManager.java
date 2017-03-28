package com.pmcc.core.manager;

import com.pmcc.base.util.ResultBean;
import com.pmcc.core.dao.OmPttitleDao;
import com.pmcc.core.domain.OmPttitle;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ${zhounan} on 2016/2/18.
 */

@Transactional
@Repository
public class OmPttitleManager  extends BaseManager<OmPttitle, String>{
    @Autowired
    OmPttitleDao   omPttitleDao;



    public List<OmPttitle> query(String paprm){

        return    omPttitleDao.query(paprm);
    }


}
