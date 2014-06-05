package com.oa.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;

public interface SuperDaoInte {

	public abstract Serializable add(Object model);

	public abstract void delete(Object model);

	public abstract void update(Object model);

	public abstract Object select(Class clazz, Serializable id);
	public Object select(String hql);

	public abstract List<Object> find(String hql);

	// login- user
	public abstract Object check(String hql, Object[] condition);

	public abstract List<Object> getAllObjects(Class clazz, String PartHql);
	
	public List<Object> getAllObjects(String CompleteHql);
	
	public List<Object> getpage(int index, String CompleteHql);
	
	public abstract List<Object> getPage(int index, Class clazz, String PartHql);

	public abstract HibernateTemplate getHibernateTemplate();

	@Resource
	public abstract void setHibernateTemplate(
			HibernateTemplate hibernateTemplate);
	

	public void deleteList(Class clazz,Object []ids,String hql);
	public List<Object> getChoice(List condition, String hql, String sign);
	public List<Object> getPageChoice(List condition, String hql, String sign,
			int index);
	
	public abstract List<Object[]> finds(String sql);
	public List<Object> getDistinctAllObject(Class clazz, String sql);
}