package com.frame.common.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.frame.common.page.Pagination;
import com.frame.common.page.SimplePage;
import com.frame.common.utils.Reflections;

public abstract class BasisDaoImpl<T> extends HibernateBaseDao implements BasisDao<T> {
	private Class<T> entityClass;
	/**
	 * 构造方法，根据实例类自动获取实体类类型
	 */
	public BasisDaoImpl() {
		entityClass = Reflections.getClassGenricType(getClass());
		//this.getSession().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);

	}
	public Session session(){
		return this.getSession();
	}
	
	@Override
	@Transactional(readOnly = false)
	public void save(T obj) {
		
		getSession().save(obj);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(T obj) {
		// TODO Auto-generated method stub
		Assert.notNull(obj);
		getSession().update(obj);
	}

	@Override
	@Transactional(readOnly = false)
	public void saveOrUpdate(T obj) {
		// TODO Auto-generated method stub
		//this.getSession().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		Assert.notNull(obj);
		this.getSession().saveOrUpdate(obj);

	}
	@Override
	@Transactional(readOnly = false)
    public <T> T merge(T entity) {
        Assert.notNull(entity);
        try {
            // 获取实体编号
            Object id = null;
            for (Method method : entity.getClass().getMethods()) {
                Id idAnn = method.getAnnotation(Id.class);
                if (idAnn != null) {
                    id = method.invoke(entity);
                    break;
                }
            }
            // 插入前执行方法
            if (StringUtils.isBlank((String) id)) {
                for (Method method : entity.getClass().getMethods()) {
                    PrePersist pp = method.getAnnotation(PrePersist.class);
                    if (pp != null) {
                        method.invoke(entity);
                        break;
                    }
                }
            }
            // 更新前执行方法
            else {
                for (Method method : entity.getClass().getMethods()) {
                    PreUpdate pu = method.getAnnotation(PreUpdate.class);
                    if (pu != null) {
                        method.invoke(entity);
                        break;
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return (T) getSession().merge(entity);
    }

	@Override
	public <T> T get(Serializable id) {
		// TODO Auto-generated method stub
		return (T) getSession().get(entityClass, id);
	}

	@Override
	public <T> T get(Class<T> classEntity, Serializable id) {
		// TODO Auto-generated method stub
		return (T) getSession().get(classEntity, id);
	}

	@Override
	public <T> T get(String entityName, Serializable id) {
		// TODO Auto-generated method stub
		return (T) getSession().get(entityName, id);
	}
	@Override
	public <T> T load(Serializable id) {
		return (T) getSession().load(entityClass, id);
	}
	@Override
	@Transactional(readOnly = false)
	public void delete(T obj) {
		// TODO Auto-generated method stub
		getSession().delete(obj);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteByID(Serializable id) {
		// TODO Auto-generated method stub
		this.getSession().delete(get(id));
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteByID(Class<T> classEntity, Serializable id) {
		// TODO Auto-generated method stub
		this.getSession().delete(get(classEntity,id));
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteByID(String entityName, Serializable id) {
		// TODO Auto-generated method stub
		this.getSession().delete(get(entityName,id));
	}
	@Override
	@Transactional(readOnly = false)
	public void deleteList(List list) {
		// TODO Auto-generated method stub
		for(Object o:list){
		   this.getSession().delete(o);
		}
	}

	@Override
	public List<T> find(String hql) {
		// TODO Auto-generated method stub
		return (List<T>) findByHql(hql);
	}

	@Override
	public List<T> find(String hql, Object... objs) {
		// TODO Auto-generated method stub
		return (List<T>) findByHql(hql,objs);
	}
	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return (List<T>) findByHql("from "+entityClass.getName());
	}
	@Override
	public List findObjs(String hql) {
		// TODO Auto-generated method stub
		return findByHql(hql);
	}
	@Override
	public List findObjs(String hql,Object... objs) {
		// TODO Auto-generated method stub
		return findByHql(hql,objs);
	}
	@Override
	public Pagination find(int pageNo) {
		// TODO Auto-generated method stub
		DetachedCriteria dc = createDetachedCriteria();
		return find(pageNo,dc);
	}
	@Override
	public Pagination find(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		DetachedCriteria dc = createDetachedCriteria();
		return find(pageNo,pageSize,dc);
	}
	@Override
	public Pagination find(int pageNo, DetachedCriteria detachedCriteria) {
		// TODO Auto-generated method stub
		return find(pageNo, SimplePage.DEF_COUNT, detachedCriteria);
	}
	@Override
	public Pagination find(int pageNo, int pageSize, DetachedCriteria detachedCriteria) {
		// TODO Auto-generated method stub
		return find(pageNo, pageSize, detachedCriteria,
				Criteria.DISTINCT_ROOT_ENTITY);
	}
	
	/**
	 * 使用检索标准对象分页查询
	 * 
	 * @param pageNo
	 * @param detachedCriteria
	 * @param resultTransformer
	 * @return
	 */
	public Pagination find(int pageNo, int pageSize,
			DetachedCriteria detachedCriteria,
			ResultTransformer resultTransformer) {
		// get count
		int totalCount = count(detachedCriteria);
		Pagination p = new Pagination(pageNo, pageSize, totalCount);
		Criteria criteria = detachedCriteria
				.getExecutableCriteria(getSession());
		criteria.setResultTransformer(resultTransformer);
		// set page
		criteria.setFirstResult(p.getFirstResult());
		criteria.setMaxResults(p.getPageSize());

		p.setList(criteria.list());
		return p;
	}
	/**
	 * 创建与会话无关的检索标准对象
	 * 
	 * @param criterions
	 *            Restrictions.eq("name", value);
	 * @return
	 */
	public DetachedCriteria createDetachedCriteria(Criterion... criterions) {
		DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
		for (Criterion c : criterions) {
			dc.add(c);
		}
		return dc;
	}
	/**
	 * 使用检索标准对象查询记录数
	 * 
	 * @param detachedCriteria
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public int count(DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria
				.getExecutableCriteria(getSession());
		int totalCount = 0;
		try {
			// Get orders
			Field field = CriteriaImpl.class.getDeclaredField("orderEntries");
			field.setAccessible(true);
			List orderEntrys = (List) field.get(criteria);
			// Remove orders
			field.set(criteria, new ArrayList());
			// Get count
			criteria.setProjection(Projections.rowCount());
			totalCount = NumberUtils.toInt(criteria.uniqueResult().toString());
			// Clean count
			criteria.setProjection(null);
			// Restore orders
			field.set(criteria, orderEntrys);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return totalCount;
	}

	
}
