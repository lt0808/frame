package com.frame.common.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;

import com.frame.common.page.Pagination;

public interface BasisService<T> {
	public void save(T entity);
	public void update(T entity);
	public void saveOrUpdate(T entity);
	public T merge(T entity);
	<T> T get(Serializable id);
	<T> T get(Class<T> classEntity,Serializable id);
	<T> T get(String entityName,Serializable id);
	<T> T load(Serializable id);
	public void delete(T entity);
	public void deleteByID(Serializable id);
	public void deleteByID(Class<T> classEntity,Serializable id);
	public void deleteByID(String entityName,Serializable id);
	public void deleteList(List list);
	public List<T> find(String hql);
	public List<T> find(String hql,Object...objects);
	public List<T> findAll();
	public List findObjs(String hql);
	public List findObjs(String hql,Object...objects);
	public Pagination find(int pageNo);
	public Pagination find(int pageNo, DetachedCriteria detachedCriteria);
	public Pagination find(int pageNo, int pageSize);
	public Pagination find(int pageNo, int pageSize,DetachedCriteria detachedCriteria);
	public Map<String,Object> findToMap(int pageNo);
	public Map<String,Object> findToMap(int pageNo, DetachedCriteria detachedCriteria);
	public Map<String,Object> findToMap(int pageNo, int pageSize);
	public Map<String,Object> findToMap(int pageNo, int pageSize,DetachedCriteria detachedCriteria);
	public DetachedCriteria createDetachedCriteria(Criterion... criterions);
}
