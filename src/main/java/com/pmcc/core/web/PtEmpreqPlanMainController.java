package com.pmcc.core.web;

import com.pmcc.core.domain.PtEmpreqPlanMain;
import com.pmcc.core.manager.PtEmpreqPlanMainManager;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhounan on 2016/2/23.
 */

@Controller
@RequestMapping("ptEmpreqPlanMain")
public class PtEmpreqPlanMainController  extends BaseAjaxController<PtEmpreqPlanMain, String>{
    @Autowired
    PtEmpreqPlanMainManager  ptEmpreqPlanMainManager;

    @Override
    public String beforeQuery(Criteria criteria, HttpServletRequest request) {

        String sDate=request.getParameter("startDate");
        String eDate=request.getParameter("endDate");
        if (sDate!=null&&eDate!=null){
        Date startDate= null;
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Date endDate= null;
        try {
            endDate = new SimpleDateFormat("yyyy-MM-dd").parse(eDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

            criteria.add(Restrictions.between("rptDate", startDate, endDate));
        }else{

        }
        return null;
    }
}
