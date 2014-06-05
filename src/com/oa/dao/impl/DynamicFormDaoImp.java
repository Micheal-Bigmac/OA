package com.oa.dao.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.oa.dao.DynamicFormDao;
import com.oa.model.DynamicForm;
@Component("dynamicFormDao")
public class DynamicFormDaoImp implements DynamicFormDao {
	private SuperDao superDao;

	public Serializable addOrUpdateDynamicForm(DynamicForm form) {
//		DynamicForm dynamicForm=(DynamicForm) superDao.select(DynamicForm.class, form.getId());
		String hql="from DynamicForm form where form.workFlow.id=?";
		DynamicForm dynamicForm=(DynamicForm) superDao.check(hql, new Object[]{form.getWorkFlow().getId()});
		if(dynamicForm==null){
			return superDao.add(form);
		}
		return dynamicForm.getId();
	}
	public DynamicForm getDynamicFormByWorkFlow(String hql,Object[] condition){
		return (DynamicForm) superDao.check(hql, condition);
	}
	public void deleteListsDynamicForm(Class clazz, String[] ids, String hql){
		superDao.deleteList(clazz, ids, hql);
	}

	public void deleteDynamicForm(DynamicForm form) {
		superDao.delete(form);
	}

	public SuperDao getSuperDao() {
		return superDao;
	}

	@Resource
	public void setSuperDao(SuperDao superDao) {
		this.superDao = superDao;
	}

	public DynamicForm getDynamicForm(Class clazz, Serializable id) {
		return (DynamicForm) superDao.select(clazz, id);
	}

}
