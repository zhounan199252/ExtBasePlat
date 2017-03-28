package com.pmcc.core.dao;

import com.pmcc.core.domain.BaseButtons;
import com.pmcc.core.domain.Dict;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by LvXL on 2016/2/3.
 */
@Repository
public class DictDao extends BaseDao<Dict, String>{

    /**
     * 查询下拉树
     * @param parentId
     * @return
     */
    public List<Dict> queryToTreePicker(String parentId) {

        DetachedCriteria criteria = DetachedCriteria.forClass(Dict.class);
        if(parentId != null && !"".equals(parentId)){
            criteria.add(Restrictions.eq("parentId", parentId));
        }
        criteria.addOrder(Order.asc("sortno"));
        return (List<Dict>) hibernateTemplate.findByCriteria(criteria);
    }

    /**
     * 查询下拉框
     * @param parentCode
     * @return
     */
    public List<Dict> queryToCombo(String parentCode) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Dict.class);
        if(parentCode != null && !"".equals(parentCode)){
            criteria.add(Restrictions.eq("parentCode", parentCode));
        }
        criteria.addOrder(Order.asc("sortno"));
        return (List<Dict>) hibernateTemplate.findByCriteria(criteria);
    }

}
