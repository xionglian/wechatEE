package com.dao;
import com.entity.ScheduleMember;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.hql.internal.ast.QuerySyntaxException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.*;

public class  BaseDao<T> {

    @Autowired
    private SessionFactory sessionFactory;

    public Session openSession()
    {
        return sessionFactory.openSession();
    }

    /**
     * 得到当前session
     *
     * @return
     */
    public Session getCurrentSession()
    {
        return sessionFactory.getCurrentSession();
    }

    /**
     * 存储实体
     *
     * @param entity
     */
    public void save(T entity)
    {
        this.getCurrentSession().save(entity);
    }
    /**
     * 删除实体
     *
     * @param entity
     */
    public ScheduleMember delete(T entity)
    {
        this.getCurrentSession().delete(entity);
        return null;
    }

    /**
     * 根据HQL语句删除实体
     *
     * @param hql
     * @param params
     * @param query
     */
    public boolean deleteByHql(String hql, Map<String, Object> params, Query query)
    {
        try
        {
            if (query == null)
                query = this.getCurrentSession().createQuery(hql);
            if (params != null && params.size() > 0)
                this.setParameters(query, params);
            query.executeUpdate();
//			this.getCurrentSession().beginTransaction().commit();
        } catch (Exception e)
        {
            this.getCurrentSession().close();
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 更新实体
     */
    public void update(T entity)
    {
        this.getCurrentSession().update(entity);
    }

    /**
     * 合并实体
     *
     * @param entity
     */
    public void merge(T entity)
    {
        this.getCurrentSession().merge(entity);
    }

    /**
     * persist操作
     *
     * @param entity
     */
    public void persist(T entity)
    {
        this.persist(entity);
    }

    /**
     * 存储或者更新
     *
     * @param entity
     */
    public void saveOrUpdate(T entity)
    {
        this.getCurrentSession().saveOrUpdate(entity);
    }

    /**
     * load一个实体
     *
     * @param c
     * @param id
     * @return
     */
    public T load(Class<T> c, Serializable id)
    {
        return (T) this.getCurrentSession().load(c, id);
    }

    /**
     * get方法得到一个实体
     *
     * @param c
     * @param id
     * @return
     */
    public T get(Class<T> c, Serializable id)
    {
        return (T) this.getCurrentSession().get(c, id);
    }

    /**
     * 得到实体列表第一个
     */
    public T getCertain(String hql, Map<String, Object> params, Query query)
    {
        List<T> list = this.findByHql(hql, params, query);
        if (list != null && list.size() > 0)
        {
            return list.get(0);
        }
        return null;
    }

    /**
     * 得到实体列表
     *
     * @param hql
     * @param params
     * @param query
     * @return
     */
    public List<T> findByHql(String hql, Map<String, Object> params, Query query)
    {
        List<T> list = new ArrayList<T>();
        try
        {
            if (query == null) {
                query = this.getCurrentSession().createQuery(hql);
            }
        } catch (QuerySyntaxException e)
        {
            e.printStackTrace();
        }
        if (params != null && params.size() > 0)
        {
            this.setParameters(query, params);
        }
        try
        {
            list = query.list();
        } catch (Exception e)
        {
            this.getCurrentSession().close();
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 分页查询
     *
     * @param hql
     * @param page
     * @param rows
     * @param params
     * @param query
     * @return
     */
    public List<T> listByPage(String hql, int page, int rows,
                              Map<String, Object> params, Query query)
    {
        try
        {
            if (query == null)
                query = this.getCurrentSession().createQuery(hql);
        } catch (QuerySyntaxException e)
        {
            e.printStackTrace();
        }
        if (params != null && params.size() > 0)
        {
            this.setParameters(query, params);
        }
        return query.setFirstResult((page - 1) * rows).setMaxResults(rows)
                .list();
    }
    /**
     * 获取查询条数
     */
    public int getAllTotal(String hql,Map<String, Object> params, Query query){
        try
        {
            if (query == null)
                query = this.getCurrentSession().createQuery(hql);
        } catch (QuerySyntaxException e)
        {
            e.printStackTrace();
        }
        if (params != null && params.size() > 0)
        {
            this.setParameters(query, params);
        }
        int count=query.list().size();
        return count;
    }
    /**
     * 设置参数
     *
     * @param query
     * @param params
     */
    private void setParameters(Query query, Map<String, Object> params)
    {

        Set<String> keys = params.keySet();
        for (String key : keys)
        {
            Object obj = params.get(key);
            if (obj instanceof Collection<?>)
            {
                query.setParameterList(key, (Collection<?>) obj);
            } else if (obj instanceof Object[])
            {
                query.setParameterList(key, (Object[]) obj);
            } else
            {
                query.setParameter(key, obj);
            }
        }
    }

}
