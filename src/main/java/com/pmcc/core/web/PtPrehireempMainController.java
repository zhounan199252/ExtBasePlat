package com.pmcc.core.web;

import com.pmcc.core.domain.OmWorkType;
import com.pmcc.core.domain.PtPrehireempMain;
import com.pmcc.core.manager.PtPrehireempMainManager;
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
 * Created by {zhangsongwei} on 2016/2/24.
 */

@Controller
@RequestMapping("ptPrehireempMain")
public class PtPrehireempMainController extends BaseAjaxController<PtPrehireempMain, String> {

    @Autowired
    PtPrehireempMainManager ptPrehireempMainManager;

//    @Override
//    public String beforeSave(PtPrehireempMain model, HttpServletRequest request){
//        model.setManaworktypeId(model.getWorktypeCatalog());
//        if(model.getWorktypeId().equals("")) {
//            model.setWorktypeCatalog(HttpUtil.getString(request, "worktypeCatalog"));
//        }
//        return null;
//    }

//    @RequestMapping(value = "/getWorkTree", method = RequestMethod.GET)
//    @ResponseBody
//    public List<TreeNode> getWorkTree(String  manaworktypeId){
//        if(manaworktypeId.equals("")){
//            manaworktypeId="0";
//        }
//        List<OmWorkType>  list1= workTypeManager.query(manaworktypeId);
//        List<TreeNode> listnode = new ArrayList<TreeNode>();
//        for(OmWorkType info : list1){
//            listnode.add(covert(info));
//        }
//        return  listnode;
//    }
//
//    public TreeNode covert(OmWorkType b){
//        OrgTreeNode tn = new OrgTreeNode();
//        tn.setOid(b.getWorktypeId());
//        tn.setId(b.getWorktypeId());
//        tn.setText(b.getWorktypeName());
//
//        List<OmWorkType>  list3 = workTypeManager.query(b.getWorktypeId());
//        if(list3 !=null && list3.size()>0){
//            tn.setLeaf(false);
//        }else{
//            tn.setLeaf(true);
//        }
//        return tn;
//    }
}
