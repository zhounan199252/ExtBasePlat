package com.pmcc.core.manager;

import com.pmcc.base.util.ResultBean;
import com.pmcc.core.dao.BaseDao;
import com.pmcc.base.util.ServiceException;
import com.pmcc.core.dao.BaseDaoInterface;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * 基础 manager实现
 * Created by Admin on 2016/1/22.
 */
@Transactional
@Service
public abstract class BaseManager<T, PK extends Serializable> implements BaseManagerInterface<T, PK> {

    public BaseManager() {
    }

    @Autowired
    BaseDaoInterface<T, PK> baseDao;

    @Transactional(readOnly = true)
    public Criteria createCriteria() {
        return baseDao.createCriteria(new Criterion[0]);
    }

    @Transactional(readOnly = true)
    public T get(PK id) throws ServiceException {
        return baseDao.get(id);
    }

    @Transactional(readOnly = true)
    public T get(Class<T> entityClass, Serializable id) {
        return baseDao.get(entityClass, id);
    }

    /**
     * 保存
     * @param entity
     * @return 保存数据的主键
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(T entity) {
        try {
            baseDao.save(entity);
        } catch (Exception var3) {
            var3.printStackTrace();
            throw new ServiceException(entity.toString() + "对象保存失败");
        }
    }

    /**
     * 查询
     * @param criteria
     * @param pageNo
     * @param limit
     * @return
     */
    @Transactional(readOnly = true)
    public ResultBean pagedQuery(Criteria criteria, int pageNo, int limit) {
        return baseDao.pagedQuery(criteria, pageNo, limit);
    }

    /**
     * 逻辑删除
     * @param arr
     * @param ids
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(String[] arr, String[] ids) {
        String column = arr == null ? "" : arr[0];
        String val = arr == null ? "" : arr[1];
        baseDao.updateByColumn(column, val, ids);
    }

    /**
     * 物理删除
     * @param id
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(PK id) {
        baseDao.delete(id);
    }

    /**
     * 执行SQL语句
     * @param sql
     * @param parameters
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void executeSQL(String sql, List parameters){
        baseDao.executeSQL(sql, parameters);
    }

    /**
     * 通过SQL查询
     * @param sql
     * @param parameters
     * @return
     */
    @Transactional(readOnly = true)
    public List queryBySQL(String sql, List parameters){
        return baseDao.queryBySQL(sql, parameters);
    }

    /**
     * 批量保存
     * @param entitys
     * @return 返回保存的数量
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public int saveAll(List<T> entitys) {

        return baseDao.saveAll(entitys);
    }


}
