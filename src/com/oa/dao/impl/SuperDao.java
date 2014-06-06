package com.oa.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.oa.dao.SuperDaoInte;
import com.oa.model.Module;

@Component("superDao")
public class SuperDao implements SuperDaoInte {

	private HibernateTemplate hibernateTemplate;

	public Serializable add(Object model) {
		Serializable id = hibernateTemplate.save(model);
		return id;
	}

	public void delete(Object model) {
		hibernateTemplate.delete(model);
	}

	public void update(Object model) {
		hibernateTemplate.update(model);
	}

	public Object select(Class clazz, Serializable id) {
		return hibernateTemplate.get(clazz, id);
	}

	public Object select(String hql) {
		return hibernateTemplate.find(hql);
	}

	public List<Object> find(String hql) {
		return hibernateTemplate.find(hql);
	}
	
	public List<Object> getChoice(List condition, String hql, String sign) {
		List<Object> list = hibernateTemplate.getSessionFactory()
				.getCurrentSession().createQuery(hql)
				.setParameterList(sign, condition).list();
		return list;
	}

	public List<Object> getPageChoice(List condition, String hql, String sign,
			int index) {
		List<Object> list = hibernateTemplate.getSessionFactory()
				.getCurrentSession().createQuery(hql)
				.setParameterList(sign, condition)
				.setFirstResult((index - 1) * 10)
				.setMaxResults(10)
				.list();
		return list;
	}

	// login- user
	public Object check(String hql, Object[] condition) {
		List list = hibernateTemplate.find(hql, condition);
		if (list.size() != 0) {
			return list.get(0);
		}
		return null;
	}

	public List<Object> getAllObjects(Class clazz, String PartHql) {
		List<Object> list = hibernateTemplate.find("from " + clazz.getName()
				+ " s where 1=1 " + (PartHql == null ? "" : PartHql));
		return list;
	}

	public List<Object> getAllObjects(String CompleteHql) {
		List<Object> lists = hibernateTemplate.find(CompleteHql);
		return lists;
	}

	public List<Object> getpage(int index, String CompleteHql) {
		List<Object> list = hibernateTemplate.getSessionFactory()
				.getCurrentSession().createQuery(CompleteHql)
				.setFirstResult((index - 1) * 10).setMaxResults(10).list();
		return list;
	}

	public List<Object> getPage(int index, Class clazz, String PartHql) {
		List<Object> list = null;
		String query = "from " + clazz.getName() + " s where 1=1 "
				+ (PartHql == null ? "" : PartHql);
		System.out.println(query);
		list = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery(query).setFirstResult((index - 1) * 10)
				.setMaxResults(10).list();

		return list;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource(name = "hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void deleteList(Class clazz, Object[] ids, String hql) {
		hql += " in ( ";
		for (int i = 0; i < ids.length; i++) {
			hql += ids[i] + ",";

		}
		hql = hql.substring(0, hql.lastIndexOf(","));
		hql += " )";
		hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery(hql).executeUpdate();
	}
	public List<Object[]> finds(String hql) {
		return hibernateTemplate.find(hql);
	}
	
	public List<Object> getDistinctAllObject(Class clazz, String sql) {
		System.out.println("select distinct(s) from " + clazz.getName()
				+ " s where 1=1 " + (sql == null ? "" : sql));
		List<Object> list = hibernateTemplate.find("select distinct(s) from " + clazz.getName()
				+ " s where 1=1 " + (sql == null ? "" : sql));
		return list;
	}

	public List<Object> getPage(int i, Class clazz,
			String findCondition, String textfield) {
		String hql = "";
		if(!textfield.equals("")) {
			if(findCondition.equalsIgnoreCase("id")) {
				hql = "and s." + findCondition + "=" + textfield;
			} else {
				hql = "and s." + findCondition + " like '%" + textfield +"%'";
			}
		} else {
			hql = "and s.pid = null";
		}
		
		return getPage(i, clazz, hql);
	}

	public List getPage(int i, String clazz, String findCondition,
			String textfield, String hql) {
		return getPage(i, clazz, hql);
	}

	public List<Object> getPage(int index, String clazz, String PartHql) {
		List<Object> list = null;
		String query = "from " + clazz + " s where 1=1 "
				+ (PartHql == null ? "" : PartHql);
		System.out.println(query);
		list = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery(query).setFirstResult((index - 1) * 10)
				.setMaxResults(10).list();
		return list;
	}

	public List getAllObjects(String className, String PartHql) {
		List<Object> list = hibernateTemplate.find("from " + className
				+ " s where 1=1 " + (PartHql == null ? "" : PartHql));
		return list;
	}

}
