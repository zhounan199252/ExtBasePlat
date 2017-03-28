package com.pmcc.core.web;

import com.pmcc.core.domain.Dict;
import com.pmcc.core.manager.DictManager;
import com.pmcc.utils.AppUtils;
import com.pmcc.utils.DateUtil;
import com.pmcc.utils.OnlineUser;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 字典值 Controller
 * Created by LvXL on 2016/2/2.
 */
@Controller
@RequestMapping("dict")
public class DictController extends BaseAjaxController<Dict, String>{

    @Autowired
    DictManager dictManager;

    @Override
    public String beforeSave(Dict model, HttpServletRequest request){

        if(id == null){

            OnlineUser user = AppUtils.getOnlineUser(WebUtils.getSessionId(request));
            model.setCreator(user.getUserId());
            model.setCreateTime(new Timestamp((new Date()).getTime()));
        }

        return null;
    }

    /**
     * 查询下拉树
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryToTreePicker", method = RequestMethod.GET)
    @ResponseBody
    public Dict queryToTreePicker(String id, String type) {

        return dictManager.queryToTreePicker(id, type);
    }

    /**
     * 查询下拉框
     * @param parentCode
     * @return
     */
    @RequestMapping(value = "/queryToCombo", method = RequestMethod.GET)
    @ResponseBody
    public List<Dict> queryToCombo(String parentCode) {

        return dictManager.queryToCombo(parentCode);
    }

}
