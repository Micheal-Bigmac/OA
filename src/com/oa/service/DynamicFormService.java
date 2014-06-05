package com.oa.service;

import java.io.Serializable;

import com.oa.model.DynamicForm;

public interface DynamicFormService {

	public Serializable addOrUpdateDynamicForm(DynamicForm form);
	
	public void deleteDynamicForm(DynamicForm form);
	
	public void deleteListsDynamicForm( String[] ids);
	public DynamicForm getDynamicForm(Serializable id);
	
	public DynamicForm getDynamicFormByWorkFlow(int workflowId);
}
