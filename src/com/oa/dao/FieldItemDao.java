package com.oa.dao;

import java.io.Serializable;
import java.util.List;

import com.oa.model.FieldItem;

public interface FieldItemDao {

	public List<FieldItem> getPageFieldItems(int index,String hql,Class clazz);
	
	public List<FieldItem> getAllFieldItems(String hql,Class clazz);
	
	public Serializable addFieldItem(FieldItem item);
	
	public void updateFieldItem(FieldItem item);
	
	public void deleteFieldItem(FieldItem item);
	
	public void DeleteListFieldItems(Class clazz, String[] ids,String hql);
	
}
