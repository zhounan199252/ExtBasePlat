package com.pmcc.core.dao.daofacade;


import com.pmcc.core.domain.BaseButtons;
import com.pmcc.core.domain.BaseDictionary;

import java.util.List;

/**
 * Created by yaonan on 2015/12/21.
 */
public interface BaseUtilDaoFacade {

    public  List<BaseDictionary> getList(String fileName);

}
