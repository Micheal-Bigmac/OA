package com.oa.dao;

import java.io.Serializable;

import com.oa.model.DynamicForm;

public interface DynamicFormDao {

	public Serializable addOrUpdateDynamicForm(DynamicForm form);
	
	public void deleteDynamicForm(DynamicForm form);
	
	public void deleteListsDynamicForm(Class clazz, String[] ids, String hql);
	
	public DynamicForm getDynamicForm(Class clazz,Serializable id);
	
	public DynamicForm getDynamicFormByWorkFlow(String hql,Object[] condition);
}
