package com.pmcc.core.web;

import com.pmcc.core.domain.OmSpecialty;
import com.pmcc.core.manager.OmSpecialtyManager;
import com.pmcc.utils.HttpUtil;
import com.pmcc.utils.OrgTreeNode;
import com.pmcc.utils.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by H on 2016/2/19.
 */
@Controller
@RequestMapping("omSpecialty")
public class OmSpecialtyController extends BaseAjaxController<OmSpecialty,String> {
    @Autowired
    OmSpecialtyManager omSpecialtyManager;

    @Override
    public String beforeSave(OmSpecialty model,HttpServletRequest request){
        model.setMaNaSpecId(model.getSpecSeries());
        if (model.getMaNaSpecId().equals("")){
            model.setSpecSeries(HttpUtil.getString(request,"specSeries"));
        }
        return null;
    }

    @RequestMapping(value = "/omSpecialtyTree",method = RequestMethod.GET)
    @ResponseBody
    public List<TreeNode>GetOmSpecialtyTree(String maNaSpecId){
        if (maNaSpecId.equals("")){
            maNaSpecId="0";
        }
        List<OmSpecialty>list=omSpecialtyManager.query(maNaSpecId);
        List<TreeNode> nodeList=new ArrayList<TreeNode>();
        for (OmSpecialty info:list){
            nodeList.add(covert(info));
        }
        return nodeList;
    }

    private TreeNode covert(OmSpecialty info) {
        OrgTreeNode otn=new OrgTreeNode();
        otn.setOid(info.getSpecId());
        otn.setId(info.getSpecId());
        otn.setText(info.getSpecName());

        List<OmSpecialty> list=omSpecialtyManager.query(info.getSpecId());
        if (list!=null&&list.size()>0){
            otn.setLeaf(false);
        }else{
            otn.setLeaf(true);
        }
        return otn;
    }


}
