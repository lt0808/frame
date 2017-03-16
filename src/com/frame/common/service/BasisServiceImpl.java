package com.frame.common.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;

import com.frame.common.dao.BaseDao;
import com.frame.common.page.Pagination;
import com.frame.common.page.SimplePage;
import com.frame.common.utils.MyBeanUtils;
import com.frame.common.utils.StringUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


public abstract class BasisServiceImpl<T> implements BasisService<T> {

//	@Autowired 
//	private BaseDao getDao();
	public abstract BaseDao getDao();
	
	@Override
	public void save(T entity) {
		// TODO Auto-generated method stub
		getDao().save(entity);
	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		getDao().update(entity);
	}

	@Override
	public void saveOrUpdate(T entity) {
		// TODO Auto-generated method stub
		getDao().saveOrUpdate(entity);
	}
	@Override
	public T merge(T entity) {
		// TODO Auto-generated method stub
		return (T) getDao().merge(entity);
	}

	@Override
	public <T> T get(Serializable id) {
		// TODO Auto-generated method stub
		return (T) getDao().get(id);
	}

	@Override
	public <T> T get(Class<T> classEntity, Serializable id) {
		// TODO Auto-generated method stub
		return (T) getDao().get(classEntity, id);
	}

	@Override
	public <T> T get(String entityName, Serializable id) {
		// TODO Auto-generated method stub
		return (T) getDao().get(entityName, id);
	}
	@Override
	public <T> T load(Serializable id) {
		// TODO Auto-generated method stub
		return (T) getDao().load(id);
	}

	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
		getDao().delete(entity);
	}

	@Override
	public void deleteByID(Serializable id) {
		// TODO Auto-generated method stub
		getDao().deleteByID(id);
	}

	@Override
	public void deleteByID(Class<T> classEntity, Serializable id) {
		// TODO Auto-generated method stub
		getDao().deleteByID(classEntity, id);
	}

	@Override
	public void deleteByID(String entityName, Serializable id) {
		// TODO Auto-generated method stub
		getDao().deleteByID(entityName, id);
	}
	@Override
	public void deleteList(List list) {
		// TODO Auto-generated method stub
		getDao().deleteList(list);
	}

	@Override
	public List<T> find(String hql) {
		// TODO Auto-generated method stub
		return getDao().find(hql);
	}

	@Override
	public List<T> find(String hql, Object... objects) {
		// TODO Auto-generated method stub
		return getDao().find(hql, objects);
	}
	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return getDao().findAll();
	}
	@Override
	public List findObjs(String hql) {
		// TODO Auto-generated method stub
		return getDao().findObjs(hql);
	}
	@Override
	public List findObjs(String hql, Object... objects) {
		// TODO Auto-generated method stub
		return getDao().findObjs(hql,objects);
	}
	@Override
	public Pagination find(int pageNo, DetachedCriteria detachedCriteria) {
		// TODO Auto-generated method stub
		return getDao().find(pageNo, detachedCriteria);
	}
	@Override
	public Pagination find(int pageNo, int pageSize, DetachedCriteria detachedCriteria) {
		// TODO Auto-generated method stub
		return getDao().find(pageNo, pageSize, detachedCriteria);
	}
	@Override
	public Pagination find(int pageNo) {
		// TODO Auto-generated method stub
		return getDao().find(pageNo);
	}
	@Override
	public Pagination find(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return getDao().find(pageNo, pageSize);
	}
	@Override
	public Map<String,Object> findToMap(int pageNo, DetachedCriteria detachedCriteria) {
		// TODO Auto-generated method stub
		return findToMap(pageNo,SimplePage.DEF_COUNT,detachedCriteria);
	}
	@Override
	public Map<String,Object> findToMap(int pageNo, int pageSize, DetachedCriteria detachedCriteria) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map= Maps.newHashMap();
		Pagination page = getDao().find(pageNo, pageSize, detachedCriteria);
		map.put("total", page.getTotalCount());
		List<Map<String, Object>> mapList=Lists.newArrayList();
		for(int i=0;i<page.getList().size();i++){
			Map<String, Object> map2=MyBeanUtils.describe(page.getList().get(i));
			mapList.add(map2);
		}
		map.put("rows", mapList);
		return map;
	}
	@Override
	public Map<String,Object> findToMap(int pageNo) {
		// TODO Auto-generated method stub
	 return findToMap(pageNo,SimplePage.DEF_COUNT,createDetachedCriteria());
	}
	@Override
	public Map<String,Object> findToMap(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return findToMap(pageNo,pageSize,createDetachedCriteria());
	}
	
	
	@Override
	public DetachedCriteria createDetachedCriteria(Criterion... criterions) {
		// TODO Auto-generated method stub
		return getDao().createDetachedCriteria(criterions);
	}
	/**
	 * 递归返回表的树状结构
	 * @param entityTableName 实体表名
	 * @param parentFieldName 所属父类字段名
	 * @param parentid 所属父类id
	 * @return
	 */
	public List<Map<String, Object>> recursive(String entityTableName,String parentFieldName,String parentid){
		return recursive(entityTableName,parentFieldName,parentid,null,null);
	}

	/**
	 * 递归返回表的树状结构
	 * @param entityTableName 实体表名
	 * @param parentFieldName 所属父类字段名
	 * @param parentid 所属父类id
	 * @param nameField text所对应字段名
	 * @return
	 */
	public List<Map<String, Object>> recursive(String entityTableName,String parentFieldName,String parentid,String nameField){
		return recursive(entityTableName,parentFieldName,parentid,nameField,null);
	}
	/**
	 * 递归返回表的树状结构
	 * @param entityTableName 实体表名
	 * @param parentFieldName 所属父类字段名
	 * @param parentid 所属父类id
	 * @param nameField text所对应字段名,可以为null
	 * @param orderby 排序字段 。例如：sortid asc 可以为null
	 * @return
	 */
	public List<Map<String, Object>> recursive(String entityTableName,String parentFieldName,String parentid,String nameField,String orderby){
		List<Map<String, Object>> relist=Lists.newArrayList();
		String where="";
		String orderBy="";
		if(StringUtils.isEmpty(parentid)){
			where=parentFieldName+"='' or "+parentFieldName + " is null";
		}else{
			where =parentFieldName+"='"+parentid+"'";
		}
		if(!StringUtils.isEmpty(orderby)){
			orderBy=" order by "+orderby;
		}
		List list=findObjs("from "+entityTableName+" where "+where+orderBy);
		for(Object o:list){
			Map<String, Object> map=Maps.newHashMap();
			map=MyBeanUtils.describe(o);
			map.put("parentid_",parentid);
			map.put("text",StringUtils.isEmpty(nameField)?"":map.get(nameField).toString());
			map.put("children", recursive(entityTableName,parentFieldName,map.get("id").toString(),nameField,orderby));
			relist.add(map);
		}
		return relist;
	}
	
	public List<Map<String, Object>> getSelectList(String entityTableName,String nameField,int flag){
		return getSelectList(entityTableName,nameField,"",flag);
	}
	public List<Map<String, Object>> getSelectList(String entityTableName,String nameField,String where,int flag){
		List<Map<String, Object>> relist=Lists.newArrayList();
		List list=findObjs("from "+entityTableName+(StringUtils.isNotEmpty(where)?" where "+where:""));
		if(flag==1){
			Map<String, Object> m=Maps.newHashMap();
			m.put("id","");
			m.put("text", "--请选择--");
			relist.add(m);
		}
		
		for(Object o:list){
			Map<String, Object> map=Maps.newHashMap();
			map=MyBeanUtils.describe(o);
			map.put("text",StringUtils.isEmpty(nameField)?"":map.get(nameField).toString());
			relist.add(map);
		}
		return relist;
	}
	
	public Session session(){
		return getDao().session();
	}
	
}
