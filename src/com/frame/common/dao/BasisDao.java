package com.frame.common.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.transform.ResultTransformer;

import com.frame.common.page.Pagination;

public interface BasisDao<T> {
	public void save(T obj);
	public void update(T obj);
	public void saveOrUpdate(T obj);
	<T> T merge(T obj);
	<T> T get(Serializable id);
	<T> T get(Class<T> classEntity,Serializable id);
	<T> T get(String entityName,Serializable id);
	<T> T load(Serializable id);
	public void delete(T obj);
	public void deleteByID(Serializable id);
	public void deleteByID(Class<T> classEntity,Serializable id);
	public void deleteByID(String entityName,Serializable id);
	public void deleteList(List<T> list);
	public List<T> find(String hql);
	public List<T> find(String hql,Object... objs);
	public List<T> findAll();
	public List findObjs(String hql);
	public List findObjs(String hql,Object... objs);
	public Pagination find(int pageNo, DetachedCriteria detachedCriteria);
	public Pagination find(int pageNo);
	public Pagination find(int pageNo, int pageSize,DetachedCriteria detachedCriteria);
	public Pagination find(int pageNo, int pageSize);
	public DetachedCriteria createDetachedCriteria(Criterion... criterions);
}
