package com.oa.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.oa.dao.DynamicFieldDao;
import com.oa.model.DynamicField;
import com.oa.model.DynamicForm;
@Component("dynamicFieldDao")
public class DynamicFieldDaoImp implements DynamicFieldDao {
	private SuperDao superDao;

	public Serializable addDynamicField(DynamicField field) {
		return superDao.add(field);
	}
	public DynamicField getDynamicField(Class clazz, Serializable id){
		return (DynamicField)superDao.select(clazz, id);
	}
	public void deleteDynamicField(DynamicField field) {
		superDao.delete(field);
	}

	public void updateDynamicField(DynamicField field) {
		superDao.update(field);
	}

	public List<DynamicField> getPageDynamicFields(int index,Class clazz, String hql) {
		return objectToDynamicField(superDao.getPage(index, clazz, hql));
	}

	private List<DynamicField> objectToDynamicField(List<Object> list){
		List<DynamicField> dynamicFields =new ArrayList<DynamicField>();
		for(Object object : list){
			dynamicFields.add((DynamicField)object);
		}
		return dynamicFields;
	}
	public List<DynamicField> getAllDynamicFields( Class clazz, String hql) {
		return objectToDynamicField(superDao.getAllObjects(clazz, hql));
	}

	public void deleteDynamicFields(Class clazz, String[] ids, String hql) {
		superDao.deleteList(clazz, ids, hql);
	}

	public List getAllFieldInput(Class clazz, String hql) {
		return superDao.getAllObjects(clazz, hql);
	}

	public List getAllFieldType(Class clazz, String hql) {
		return superDao.getAllObjects(clazz, hql);
	}

	public SuperDao getSuperDao() {
		return superDao;
	}
	
	@Resource
	public void setSuperDao(SuperDao superDao) {
		this.superDao = superDao;
	}


}
