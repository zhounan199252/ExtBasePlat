package com.pmcc.core.manager;

import com.pmcc.core.dao.DictDao;
import com.pmcc.core.domain.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LvXL on 2016/2/3.
 */
@Transactional
@Service
public class DictManager extends BaseManager<Dict, String>{

    @Autowired
    DictDao dictDao;

    /**
     * 查询下拉树
     * @param id
     * @return
     */
    public Dict queryToTreePicker(String id, String type) {

        // 查询根节点
        List<Dict> list = dictDao.queryToTreePicker("-1");
        Dict dic = new Dict();
        dic.setDictName("root");
        Dict dict = null;
        if(list != null && list.size() > 0){
            dict = this.getChildList(list.get(0), type);
            List<Dict> reList = new ArrayList<Dict>();
            reList.add(dict);
            dic.setChildren(reList);
            return dic;
        }else{
            return null;
        }
    }

    /**
     * 递归查询子节点
     * @param dict
     * @param type
     * @return
     */
    public Dict getChildList(Dict dict, String type){

        String pid = dict.getId();

        if(pid != null && !"".equals(pid)){
            List<Dict> clist = dictDao.queryToTreePicker(pid);
            if(clist != null && clist.size() > 0){
                // 有子节点
                for(Dict dic : clist){
                    this.getChildList(dic, type);
                }
                dict.setChildren(clist);
                // 下拉Grid显示checkbox，下拉树不显示
                if(type != null && !"".equals(type)){
                    dict.setChecked(false);
                }
                dict.setExpanded(false);
                dict.setLeaf(false);
            }else{
                if(type != null && !"".equals(type)){
                    dict.setChecked(false);
                }
                dict.setExpanded(false);
                dict.setLeaf(true);
            }
        }
        return dict;
    }

    /**
     * 查询下拉框
     * @param parentCode
     */
    public List<Dict> queryToCombo(String parentCode) {
        return dictDao.queryToCombo(parentCode);
    }
}
