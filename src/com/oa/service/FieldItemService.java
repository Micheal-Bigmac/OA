package com.oa.service;

import java.io.Serializable;
import java.util.List;

import com.oa.model.FieldItem;

public interface FieldItemService {
	public List<FieldItem> getPageFieldItems(int index,String hql);
	
	public List<FieldItem> getAllFieldItems(String hql);
	
	public Serializable addFieldItem(FieldItem item);
	
	public void updateFieldItem(FieldItem item);
	
	public void deleteFieldItem(FieldItem item);
	
	public void DeleteListFieldItems( String[] ids);
}
