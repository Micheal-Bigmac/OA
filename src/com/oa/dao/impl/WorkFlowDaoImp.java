package com.oa.dao.impl;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.oa.dao.WorkFlowDao;
import com.oa.extend.JbpmCore;
import com.oa.model.Users;
import com.oa.model.WorkFlow;

@Component("workFlowDao")
public class WorkFlowDaoImp implements WorkFlowDao {
	private SuperDao superDao;
	private JbpmCore jbpmCore;

	public Serializable addWorkFlow(String processDefinition,
			String processImage) throws FileNotFoundException {
		String workFlowName = jbpmCore
				.deployProcessDefinition(processDefinition);
		WorkFlow wf= (WorkFlow) superDao.check("from WorkFlow w where w.name= ?",new Object[]{workFlowName});
		if(wf == null){
			wf = new WorkFlow();
			wf.setName(workFlowName);
			wf.setProcessDefinition(processDefinition);
			wf.setProcessImage(processImage);
		}
		return superDao.add(wf);
	}
	

	
//	public void addOrUpdateWorkflow(String processDef,String processImage) throws FileNotFoundException {
//		String workflowName = jbpmCore.deployProcessDefinition(processDef);
//		//首先根据流程名称，查询是否已有Workflow对象
////		Workflow wf = (Workflow)getSession().createQuery("select w from Workflow w where w.name = ?")
////			.setParameter(0, workflowName)
////			.uniqueResult();
//		System.out.println(workflowName+"++++++++++++++");
//		
////		Users login=userService.login("from Users u where u.account = ? and u.password= ?", new Object[]{user.getAccount(),user.getPassword()});
//
//		WorkFlow wf= (WorkFlow) superDao.check("from WorkFlow w where w.name= ?",new Object[]{workflowName});
////		System.out.println("workflowDaoImp +"+wf.toString());
//		if(wf == null){
//			wf = new WorkFlow();
//			wf.setName(workflowName);
//			wf.setProcessDefinition(processDef);
//			wf.setProcessImage(processImage);
////			getHibernateTemplate().save(wf);
//			superDao.add(wf);
//		}else{
//			wf.setProcessDefinition(processDef);
//			wf.setProcessImage(processImage);
////			getHibernateTemplate().update(wf);
//			superDao.update(wf);
//		}
//	}
	public void updateWorkFlow(String processDefinition, String processImage,int id) {
		String workFlowName = "";
		try {
			workFlowName = jbpmCore.deployProcessDefinition(processDefinition);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// 首先根据流程名称，查询是否已有Workflow对象
//		WorkFlow wf = (WorkFlow) superDao.select(
//				"from WorkFlow w where w.name= " + workFlowName).get(0);
		WorkFlow wf=new WorkFlow();
		wf.setId(id);
		// if(wf !=null){
		wf.setProcessDefinition(processDefinition);
		wf.setProcessImage(processImage);
		superDao.update(wf);
		// }
	}

	public void deleteWorkFlow(WorkFlow workFlow) {
		jbpmCore.delProcessDefinition(workFlow.getName());
		superDao.delete(workFlow);
	}
	public WorkFlow getWorkFlow(Class clazz,Serializable id){
		return (WorkFlow) superDao.select(clazz, id);
	}

	public WorkFlow findWorkFlow(Class clazz, Serializable id) {
		return (WorkFlow) superDao.select(clazz, id);
	}

	public List<WorkFlow> getAllWorkFlows(Class clazz, String hql) {
		return ObjectToWorkFlow(superDao.getAllObjects(clazz, hql));
	}

	private List<WorkFlow> ObjectToWorkFlow(List<Object> lists) {
		List<WorkFlow> workFlows = new ArrayList<WorkFlow>();
		for (Object o : lists) {
			workFlows.add((WorkFlow) o);
		}
		return workFlows;
	}

	public List<WorkFlow> getPageWorkFlows(int index, Class clazz, String hql) {
		return ObjectToWorkFlow(superDao.getPage(index, clazz, hql));
	}

	public SuperDao getSuperDao() {
		return superDao;
	}

	@Resource
	public void setSuperDao(SuperDao superDao) {
		this.superDao = superDao;
	}

	public JbpmCore getJbpmCore() {
		return jbpmCore;
	}

	@Resource
	public void setJbpmCore(JbpmCore jbpmCore) {
		this.jbpmCore = jbpmCore;
	}

}
