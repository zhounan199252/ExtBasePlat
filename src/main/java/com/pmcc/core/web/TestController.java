package com.pmcc.core.web;

import com.pmcc.core.manager.BaseManager;
import com.pmcc.core.manager.TestManager;
import com.pmcc.core.domain.Test;
import com.pmcc.utils.DateUtil;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Admin on 2016/1/25.
 */
@Controller
@RequestMapping("myTest")
public class TestController extends BaseAjaxController<Test, String> {

    @Autowired
    TestManager testManager;

    @Override
    public String beforeSave(Test model, HttpServletRequest request){

        System.out.println("-----------------" + model);
        System.out.println("-----------------" + model.getId());
        System.out.println("-----------------" + model.getName());
        return null;
    }

    @RequestMapping(value = "/testSave", method = RequestMethod.GET)
    @ResponseBody
    public void testSave(){
        System.out.println("-----------------" + entity);
    }

    @Override
    public String beforeQuery(Criteria criteria, HttpServletRequest request) {
        System.out.println("-----------------" + entity);
        System.out.println("-----------------" + entity);
        System.out.println("-----------------" + entity);

        return null;
    }

    @Override
    public String[] beforeDelete(HttpServletRequest request){

        System.out.println("-----------------" + DateUtil.getCurrentTime());
        return new String[]{"address","132456789"};
//        return null;
    }

    @Override
    public String[] getExcludes() {

        return new String[] { "hibernateLazyInitializer", "handler","organPersonRelationInfo","organPersonRelationInfo"};
    }
    @Override
    public String[] getExcludesLoad(){

        return new String[] { "hibernateLazyInitializer", "handler","organPersonRelationInfo","organPersonRelationInfo"};
    }

}
