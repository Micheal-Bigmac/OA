package com.oa.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.oa.dao.DynamicFormDao;
import com.oa.model.DynamicForm;
import com.oa.service.DynamicFormService;
@Component("dynamicFormService")
public class DynamicFormServiceImp implements DynamicFormService {

	private DynamicFormDao dynamicFormDao;
	public Serializable addOrUpdateDynamicForm(DynamicForm form) {
		return dynamicFormDao.addOrUpdateDynamicForm(form);
	}

	public void deleteDynamicForm(DynamicForm form) {
		dynamicFormDao.deleteDynamicForm(form);
	}

	public DynamicForm getDynamicFormByWorkFlow(int workflowId){
		String hql="from DynamicForm form where form.workFlow.id=?";
		return dynamicFormDao.getDynamicFormByWorkFlow(hql, new Object[]{workflowId});
	}
	
	public void deleteListsDynamicForm( String[] ids) {
		String hql="delete from DynamicForm form where form.id ";
		dynamicFormDao.deleteListsDynamicForm(DynamicForm.class, ids, hql);
	}
	
	public DynamicForm getDynamicForm(Serializable id){
		return dynamicFormDao.getDynamicForm(DynamicForm.class, id);
	}

	public DynamicFormDao getDynamicFormDao() {
		return dynamicFormDao;
	}

	@Resource
	public void setDynamicFormDao(DynamicFormDao dynamicFormDao) {
		this.dynamicFormDao = dynamicFormDao;
	}

}
