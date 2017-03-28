package com.pmcc.core.web;

import com.pmcc.base.util.ResultBean;
import com.pmcc.core.dao.daofacade.BaseOrgInfoDaoFacade;
import com.pmcc.core.dao.daoimpl.BaseOrgInfoDao;
import com.pmcc.core.domain.BaseOrgInfo;
import com.pmcc.core.domain.PtEmpreqPlanDetail;
import com.pmcc.core.manager.PtEmpreqPlanMainManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhounan on 2016/2/29.
 */
@Controller
@RequestMapping("ptEmpreqPlanDetails")
public class PtEmpreqPlanDetailsController   extends   BaseAjaxController<PtEmpreqPlanDetail,String> {
      @Autowired
    PtEmpreqPlanMainManager  ptEmpreqPlanMainManager;


    @Resource
    private BaseOrgInfoDao baseOrgInfoDao;

    @Override
    public String beforeSave(PtEmpreqPlanDetail model, HttpServletRequest request) {
        if(model.getOid().equals("")){
            model.setOid(null);
        }
        BaseOrgInfo baseOrgInfo=  baseOrgInfoDao.findBoi(model.getUnit());
        if(baseOrgInfo!=null){
            model.setOrgId(Double.parseDouble(baseOrgInfo.getOid()));
            model.setOrgCode(baseOrgInfo.getCode());
            model.setUnit(baseOrgInfo.getName());
        }
        return null;
    }
}

