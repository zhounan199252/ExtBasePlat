package com.pmcc.core.dao.daofacade;


import com.pmcc.core.domain.BaseButtons;
import com.pmcc.core.domain.BaseDictionary;

import java.util.List;

/**
 * Created by yaonan on 2015/12/21.
 */
public interface BaseDictionaryDaoFacade {

    public  List<BaseDictionary> getList(int page, int limit, String swhere, String sort, String type);

   public  Boolean saveOrUpdate(BaseDictionary dictionary);

  public   boolean deleteById(String s);

}
