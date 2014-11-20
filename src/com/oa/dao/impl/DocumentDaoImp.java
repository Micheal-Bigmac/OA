package com.oa.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.oa.dao.DocumentDao;
import com.oa.extend.JbpmCore;
import com.oa.listenner.Persistence;
import com.oa.model.Document;
import com.oa.model.DocumentProperty;
import com.oa.model.Users;
import com.oa.model.WorkFlow;

@Component("documentDao")
public class DocumentDaoImp implements DocumentDao {
	private SuperDao superDao;
	private JbpmCore jbpmCore;

	/*
	 * public void addDocument(Document document, int workflowId, int userId) {
	 * // TODO Auto-generated method stub WorkFlow wf = (WorkFlow)
	 * superDao.select(WorkFlow.class, workflowId); Users users = (Users)
	 * superDao.select(Users.class, userId);
	 * 
	 * Long processInstance = jbpmCore.addProcessInstance(wf.getName(),
	 * document); System.err.println("processInstance  " + processInstance);
	 * 
	 * // System.err.println(wf.toString()); //
	 * System.err.println(users.toString()); document.setWorkFlow(wf);
	 * document.setUsers(users); document.setProcessInstanceId(processInstance);
	 * document.setStatus(Document.New); document.setCreateTime(new Date());
	 * superDao.add(document); }
	 */

	// 添加公文
	public Serializable addDocument(Document document, int workflowId, int userId, List<DocumentProperty> props) {

		// 保存公文信息
		document.setWorkFlow((WorkFlow) jbpmCore.getJbpmTemplate().getHibernateTemplate().load(WorkFlow.class, workflowId));
		document.setUsers((Users) jbpmCore.getJbpmTemplate().getHibernateTemplate().load(Users.class, userId));
		document.setStatus(Document.New);
		document.setCreateTime(new Date());
		
		String key=Persistence.setVariable((Serializable) props);
		document.setTypePersist(key+"|properties");
		// 设置其它属性
		// document.setPropertiesMap(props);

		Serializable flag=jbpmCore.getJbpmTemplate().getHibernateTemplate().save(document);
		
		
		System.out.println("=------------========");
		System.out.println(document.toString() + " documentDaoImp addDocument ");
		if (props != null) {
			for (DocumentProperty property : props) {
				if (property != null) {
					//防止删除不掉公文
					property.setDocument(document);
					superDao.add(property);
				}
			}
		}
		// 添加流程实例
		long processInstanceId = jbpmCore.addProcessInstance(document.getWorkFlow().getName(), document, props);

		// 绑定流程实例的标识到公文对象
		document.setProcessInstanceId(processInstanceId);
		jbpmCore.getJbpmTemplate().getHibernateTemplate().update(document);
		return flag;
	}

	public void deleteDocuments(Class clazz, String[] ids, String hql) {
		superDao.deleteList(clazz, ids, hql);
	}

	public void updateDocument(Document document, int workFLowId, int userId) {
		// TODO Auto-generated method stub

	}

	public Document getDocument(Serializable id) {
		return (Document) superDao.select(Document.class, id);
	}

	// 查找正在等待审批的公文=-=-=-=-=-======================-------------------------------==============
	public List<Document> searchPageApprovingDocuments(String username, int index) {
		// System.out.println(username+"===--approving ");
		List docs = jbpmCore.searchMyTaskList(username);
		// System.out.println("DocumentDaoImp 待审公文"+docs.size());
		// System.out.println(docs.size()+"DocumentDaoImp  sdfsaf ");
		if (docs.size() == 0 || docs == null) {
			return null;
		}

		String hql = "from Document d where d.id in (:ids)";
		String sign = "ids";
		// return objectToDocuments(superDao.getChoice(docs, hql, sign));
		return objectToDocuments(superDao.getPageChoice(docs, hql, sign, index));
	}

	public List<Document> searchAllApprovingDocuments(String username) {
		// List<Integer> docs = jbpmCore.seachMyTaskList(username);
		List<Integer> docs = jbpmCore.searchMyTaskList(username);
		if (docs.size() == 0 || docs == null) {
			return null;
		}
		// System.out.println(docs.size()+"DocumentDaoImp  sdfsaf ");
		String hql = "from Document d where d.id in (:ids)";
		String sign = "ids";
		return objectToDocuments(superDao.getChoice(docs, hql, sign));
	}

	// 查找用户已审批过全部的公文
	public List<Document> searchAllApprovedDocuments(String CompleteHql) {
		return objectToDocuments(superDao.getAllObjects(CompleteHql));
	}

	// 分页显示审批过的公文
	public List<Document> searchPageApprovedDocument(int index, String CompleteHql) {
		return objectToDocuments(superDao.getpage(index, CompleteHql));
	}

	private List<Document> objectToDocuments(List<Object> lists) {
		List<Document> documents = new ArrayList<Document>();
		for (Object o : lists) {
			documents.add((Document) o);
		}
		return documents;
	}

	public void deleteDocument(Document document) {
		superDao.delete(document);
	}

	/**
	 * 我的所有公文===================
	 */
	public List<Document> searchAllMyDocument(Class clazz, int userId, boolean finished) {
		if (finished == false) {
			String hql = "from Document d where d.users.id =" + userId;
			return objectToDocuments(superDao.getAllObjects(hql));
		} else {
			String hql = "from Document d where d.users.id =" + userId + " and d.status='结束'";
			return objectToDocuments(superDao.getAllObjects(hql));
		}
	}

	public List<Document> searchPageDocument(Class clazz, int userId, int index, boolean finished) {
		if (finished == false) {
			String hql = "from Document d where d.users.id =" + userId +" and d.status!='结束' ";
			return objectToDocuments(superDao.getpage(index, hql));
		} else {
			String hql = "from Document d where d.users.id =" + userId + " and d.status='结束'";
			System.out.println(hql + " ================================ finish document");
			return objectToDocuments(superDao.getpage(index, hql));
		}
	}

	// 查找用户创建的所有公文
	public List<Document> getAllDocuments(Class clazz, String hql) {
		return objectToDocuments(superDao.getAllObjects(clazz, hql));
	}

	// 分页查找用户创建的所有公文
	public List<Document> getPageDocuments(int index, Class clazz, String hql) {
		return objectToDocuments(superDao.getPage(index, clazz, hql));
	}

	/**
	 * --------------------=======================
	 */
	public List searchNextStep(int documentId, String userId) {
		Document doc = (Document) superDao.select(Document.class, documentId);
		return jbpmCore.searchNextTransitions(doc.getProcessInstanceId(), userId);
	}

	// 提交到流程
	public void submitToWorkFlow(int userId, int documentId, String transitionName) {
		// Users user = (Users)getHibernateTemplate().load(User.class, userId);
		Users user = (Users) superDao.select(Users.class, userId);
		String username = user.getAccount();

		Document document = (Document) superDao.select(Document.class, documentId);
		long processInstanceId = document.getProcessInstanceId();
		

		String status = jbpmCore.nextStep(processInstanceId, username, transitionName);
		if("结束".equals(status)){
			String key=document.getTypePersist();
			if(key!=null){
				key=key.replaceAll("(.*)\\|.*", "$1");
				Object object=Persistence.getVariable(key);
				superDao.saveOrUpdate(object);
				Persistence.removeVariable(key);
			}
		}

		document.setStatus(status);
		superDao.update(document);
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
