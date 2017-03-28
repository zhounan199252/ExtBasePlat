package com.pmcc.core.dao.daofacade;


import com.pmcc.core.domain.BaseButtons;
import com.pmcc.core.domain.BaseRoleInfo;

import java.util.List;

/**
 * Created by yaonan on 2015/12/21.
 */
public interface BaseButtonsDaoFacade {

    public  List<BaseButtons> getButtonsList(int page, int limit, String swhere, String sort, String type);

   public  Boolean saveOrUpdate(BaseButtons buttons);

  public   boolean deleteById(String s);

}
