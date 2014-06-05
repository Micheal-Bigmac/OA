package com.oa.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.oa.model.DynamicField;
import com.oa.model.DynamicForm;

public interface DynamicFieldDao {

	public Serializable addDynamicField(DynamicField field);
	
	public void deleteDynamicField(DynamicField field);
	
	public DynamicField getDynamicField(Class clazz, Serializable id);
	
	public void updateDynamicField(DynamicField field);
	
	public List<DynamicField> getPageDynamicFields(int index,Class clazz,String hql);
	
	public List<DynamicField> getAllDynamicFields(Class clazz, String hql);
	
	public void deleteDynamicFields(Class clazz, String[] ids,String hql);
	
	public List getAllFieldInput(Class clazz,String hql);
	
	public List getAllFieldType(Class clazz,String hql);
}
