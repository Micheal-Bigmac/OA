package com.oa.service;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import com.oa.model.WorkFlow;

public interface WorkFlowService {

	public abstract Serializable add(String processDefinition,
			String processImage);
	
//	public  void addOrUpdateWorkflow(String processDefinition,String processImage);

	public abstract void updateWorkFlow(String processDefinition, String processImage,int id);

	public abstract void deleteWorkFlow(WorkFlow workFlow);

	public abstract WorkFlow findWorkFlow(Serializable id);

	public abstract List<WorkFlow> getAllWorkFlows(String hql);

	public abstract List<WorkFlow> getPageWorkFlows(int index,String hql);
	
	public void deleteWorkFLow(String[] ids);
	
	public WorkFlow getWorkFlow(Serializable id);

}