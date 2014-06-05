package com.oa.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.oa.dao.FieldItemDao;
import com.oa.model.FieldItem;
@Component("fieldItemDao")
public class FieldItemDaoImp implements FieldItemDao {
	
	private SuperDao superDao;

	public List<FieldItem> getPageFieldItems(int index, String hql, Class clazz) {
		return objectToFieldItems(superDao.getPage(index, clazz, hql));
	}
	
	private List<FieldItem> objectToFieldItems(List<Object> list){
		List<FieldItem> items=new ArrayList<FieldItem>();
		for(Object object: list){
			items.add((FieldItem)object);
		}
		return items;
	}

	public List<FieldItem> getAllFieldItems(String hql, Class clazz) {
		return objectToFieldItems(superDao.getAllObjects(clazz, hql));
	}

	public Serializable addFieldItem(FieldItem item) {
		return superDao.add(item);
	}

	public void updateFieldItem(FieldItem item) {
		superDao.update(item);
	}

	public void deleteFieldItem(FieldItem item) {
		superDao.delete(item);
	}

	public void DeleteListFieldItems(Class clazz, String[] ids, String hql) {
		superDao.deleteList(clazz, ids, hql);
	}

	public SuperDao getSuperDao() {
		return superDao;
	}

	@Resource
	public void setSuperDao(SuperDao superDao) {
		this.superDao = superDao;
	}

}
