package com.oa.service.impl;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;


import com.oa.dao.WorkFlowDao;
import com.oa.extend.JbpmCore;
import com.oa.model.WorkFlow;
import com.oa.service.WorkFlowService;

/**
 * 流程操作
 * @author Big mac
 *
 */
@Component("workFlowService")
public class WorkFlowServiceImp implements WorkFlowService {
	private WorkFlowDao workFlowDao;
	private JbpmCore jbpmCore;

	/* (non-Javadoc)
	 * @see com.oa.service.impl.WorkFlowService#add(byte[], byte[])
	 */
	public Serializable add(String processDefinition,String processImage){
		try {
			return workFlowDao.addWorkFlow(processDefinition, processImage);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
//	public  void addOrUpdateWorkflow(String processDefinition,String processImage){
//		try {
//			workFlowDao.addOrUpdateWorkflow(processDefinition, processImage);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
	/* (non-Javadoc)
	 * @see com.oa.service.impl.WorkFlowService#updateWorkFlow(com.oa.model.WorkFlow)
	 */
	public void updateWorkFlow(String processDefinition, String processImage,int id){
		workFlowDao.updateWorkFlow(processDefinition, processImage,id);
	}
	/* (non-Javadoc)
	 * @see com.oa.service.impl.WorkFlowService#deleteWorkFlow(com.oa.model.WorkFlow)
	 */
	public void deleteWorkFlow(WorkFlow workFlow){
		workFlowDao.deleteWorkFlow(workFlow);
	}
	
	public void deleteWorkFLow(String[] ids){
		for(int i=0 ;i <ids.length; i++){
			WorkFlow temp=workFlowDao.findWorkFlow(WorkFlow.class, Integer.parseInt(ids[i]));
			jbpmCore.delProcessDefinition(temp.getProcessDefinition());
			workFlowDao.deleteWorkFlow(temp);
		}
//		workFlowDao.deletePersons(WorkFlow.class, ids, hql);
	}
	/* (non-Javadoc)
	 * @see com.oa.service.impl.WorkFlowService#findWorkFlow(java.io.Serializable)
	 */
	public WorkFlow findWorkFlow(Serializable id){
		return workFlowDao.findWorkFlow(WorkFlow.class, id);
	}
	/* (non-Javadoc)
	 * @see com.oa.service.impl.WorkFlowService#getAllWorkFlows(java.lang.Class, java.lang.String)
	 */
	public List<WorkFlow> getAllWorkFlows(String hql){
		return workFlowDao.getAllWorkFlows(WorkFlow.class, hql);
	}
	/* (non-Javadoc)
	 * @see com.oa.service.impl.WorkFlowService#getPageWorkFlows(java.lang.Class, java.lang.String)
	 */
	public List<WorkFlow> getPageWorkFlows(int index,String hql){
		return workFlowDao.getPageWorkFlows(index, WorkFlow.class, hql);
	}

	public WorkFlow getWorkFlow(Serializable id){
		return workFlowDao.getWorkFlow(WorkFlow.class, id);
	}
	
	public WorkFlowDao getWorkFlowDao() {
		return workFlowDao;
	}
	@Resource
	public void setWorkFlowDao(WorkFlowDao workFlowDao) {
		this.workFlowDao = workFlowDao;
	}
	
	public JbpmCore getJbpmCore() {
		return jbpmCore;
	}
	@Resource
	public void setJbpmCore(JbpmCore jbpmCore) {
		this.jbpmCore = jbpmCore;
	}
}
