package com.yc.electricity.dao.impl;

import com.yc.electricity.dao.ICommonDao;
import com.yc.electricity.util.TUtils;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by yuchuan on 3/19/16.
 */
public class CommonDaoImpl<T> extends HibernateDaoSupport implements ICommonDao<T>{

    private Class<T> clazz = TUtils.getActualType(this.getClass());

    @Resource(name = "sessionFactory")
    public void setSessionFac(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public void save(T entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public void update(T entity) {
        getHibernateTemplate().update(entity);
    }

    @Override
    public T findObjectById(Serializable id) {
        return getHibernateTemplate().get(clazz, id);
    }

    @Override
    public void deleteObjectsByIds(Serializable... ids) {
        if (ids != null && ids.length > 0) {
            for (Serializable id : ids) {
                T object= findObjectById(id);
                getHibernateTemplate().delete(object);
            }
        }
    }

    @Override
    public void deleteObjectsByList(List<T> list) {
        getHibernateTemplate().deleteAll(list);
    }

    @Override
    public List<T> findListByConditionNoPage(String condition, Object[] params, Map<String, String> orderBy) {
        String hql = "from " + clazz.getSimpleName() + " o where 1=1";
        String orderByCondition = orderByHql(orderBy);
        if (condition != null) {
            hql += condition;
        }
        hql += orderByCondition;
        return (List<T>) getHibernateTemplate().find(hql, params);

        //call hibernate session
//        List<T> list = getHibernateTemplate().execute(new HibernateCallback<Object>() {
//            @Override
//            public Object doInHibernate(Session session) throws HibernateException {
//                return  session.createQuery(hql).list();
//            }
//        });
    }

    private String orderByHql(Map<String, String> orderBy) {
        if (orderBy != null && orderBy.size() > 0) {
            StringBuffer sb = new StringBuffer();
            sb.append(" ORDER BY ");
            for (Map.Entry<String, String> map : orderBy.entrySet()) {
                sb.append(map.getKey() + " " + map.getValue() + ",");
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }
        return "";
    }
}
