package com.yc.electricity.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by yuchuan on 3/19/16.
 */
public interface IBaseDao<T> {
    void save(T entity);
    void update(T entity);
    T findObjectById(Serializable id);
    void deleteObjectsByIds(Serializable... ids);
    void deleteObjectsByList(List<T> list);
    List<T> findListByConditionNoPage(String condition, Object[] params, Map<String,String> orderBy);
}
