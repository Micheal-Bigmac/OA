package com.oa.dao;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.List;

import com.oa.model.WorkFlow;

public interface WorkFlowDao {

	public Serializable addWorkFlow(String processDefinition,
			String processImage) throws FileNotFoundException;
	
//	public void addOrUpdateWorkflow(String processDef,String processImage) throws FileNotFoundException;

	public abstract void updateWorkFlow(String processDefinition,
			String processImage, int id);

	public abstract void deleteWorkFlow(WorkFlow workFlow);

	public abstract WorkFlow findWorkFlow(Class clazz, Serializable id);

	public abstract List<WorkFlow> getAllWorkFlows(Class clazz, String hql);

	public abstract List<WorkFlow> getPageWorkFlows(int index, Class clazz,
			String hql);
	public WorkFlow getWorkFlow(Class clazz,Serializable id);

}
