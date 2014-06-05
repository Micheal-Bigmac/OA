package com.oa.service;

import java.io.Serializable;
import java.util.List;

import com.oa.model.DynamicField;
import com.oa.model.DynamicForm;

public interface DynamicFieldService {
	public Serializable addDynamicField(DynamicField field);
	
	public void deleteDynamicField(DynamicField field);
	
	public DynamicField getDynamicField( Serializable id);
	
	public void updateDynamicField(DynamicField field);
	
	public List<DynamicField> getPageDynamicFields(int index,String hql);
	
	public List<DynamicField> getAllDynamicFields( String hql);
	
	public void deleteDynamicFields(String hql,String[] ids);
	public List getAllFieldInput(String hql);
	
	public List getAllFieldType(String hql);
}
