package com.oa.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.oa.dao.DynamicFieldDao;
import com.oa.model.DynamicField;
import com.oa.model.DynamicForm;
import com.oa.model.FieldType;
import com.oa.model.FiledInput;
@Component("dynamicFieldService")
public class DynamicFieldService implements com.oa.service.DynamicFieldService {

	private DynamicFieldDao dynamicFieldDao;
	public Serializable addDynamicField(DynamicField field) {
		return dynamicFieldDao.addDynamicField(field);
	}

	public DynamicField getDynamicField( Serializable id){
		return dynamicFieldDao.getDynamicField(DynamicField.class, id);
	}
	public void deleteDynamicField(DynamicField field) {
		dynamicFieldDao.deleteDynamicField(field);
	}

	public void updateDynamicField(DynamicField field) {
		dynamicFieldDao.updateDynamicField(field);
	}

	public List<DynamicField> getPageDynamicFields(int index,String hql) {
		return dynamicFieldDao.getPageDynamicFields(index, DynamicField.class, hql);
	}

	public List<DynamicField> getAllDynamicFields( String hql) {
		return dynamicFieldDao.getAllDynamicFields(DynamicField.class, hql);
	}

	public void deleteDynamicFields(String hql ,String[] ids) {
		
		dynamicFieldDao.deleteDynamicFields(DynamicField.class, ids, hql);
	}

	public List getAllFieldInput( String hql) {
		return dynamicFieldDao.getAllFieldInput(FiledInput.class, hql);
	}

	public List getAllFieldType( String hql) {
		return dynamicFieldDao.getAllFieldType(FieldType.class, hql);
	}

	public DynamicFieldDao getDynamicFieldDao() {
		return dynamicFieldDao;
	}

	@Resource
	public void setDynamicFieldDao(DynamicFieldDao dynamicFieldDao) {
		this.dynamicFieldDao = dynamicFieldDao;
	}
}
