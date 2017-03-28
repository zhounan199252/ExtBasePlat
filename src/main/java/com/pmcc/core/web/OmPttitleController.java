package com.pmcc.core.web;

import com.pmcc.core.domain.BaseModuleInfo;
import com.pmcc.core.domain.BaseOrgInfo;
import com.pmcc.core.domain.OmPttitle;
import com.pmcc.core.domain.Test;
import com.pmcc.core.manager.OmPttitleManager;
import com.pmcc.utils.HttpUtil;
import com.pmcc.utils.OrgTreeNode;
import com.pmcc.utils.TreeNode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${zhounan} on 2016/2/18.
 */

@Controller
@RequestMapping("omPttitle")
public class OmPttitleController extends BaseAjaxController<OmPttitle, String> {
    @Autowired
    OmPttitleManager  omPttitleManager;

    @Override
    public String beforeSave(OmPttitle model, HttpServletRequest request){
       String pttSeriesName= model.getPttSeriesName();
        if(pttSeriesName!=null) {
            model.setPttSeries(pttSeriesName);
        }
        return null;
    }


    @RequestMapping(value = "/getOmPttitleTree", method = RequestMethod.GET)
    @ResponseBody
    public List<TreeNode> getOmPttitleTree(String  maNaPttId){
        if(maNaPttId.equals("")){
            maNaPttId="0";
        }
        List<OmPttitle>  list1= omPttitleManager.query(maNaPttId);
        List<TreeNode> listnode=new ArrayList<TreeNode>();
        for(OmPttitle info:list1){
            listnode.add(covert(info));
        }
        return  listnode;
    }

    public TreeNode covert(OmPttitle  b){
        OrgTreeNode tn=new OrgTreeNode();
        tn.setOid(b.getOid());
        tn.setId(b.getOid());
        tn.setText(b.getPttName());

        List<OmPttitle>  list3= omPttitleManager.query(b.getOid());
        if(list3 !=null && list3.size()>0){
            tn.setLeaf(false);
        }else{
            tn.setLeaf(true);
        }
        return tn;
    }

}
