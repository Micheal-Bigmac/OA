package com.oa.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.oa.dao.ApproveHistoryDao;
import com.oa.extend.JbpmCore;
import com.oa.model.ApproveHistory;
import com.oa.model.Document;
import com.oa.model.Users;

@Component("approveHistoryDao")
public class ApproveHistoryDaoImp implements ApproveHistoryDao {
	private SuperDao superDao;
	private JbpmCore jbpmCore;

	public void addApproveHistory(ApproveHistory approveInfo, int documentId,
			int approverId, boolean back) {
		Document document = (Document) superDao.select(Document.class,
				documentId);
		Users users = (Users) superDao.select(Users.class, approverId);
		approveInfo.setApprover(users);
		approveInfo.setDocument(document);
		superDao.add(approveInfo);
		if (back) {
			Object[] os = jbpmCore.backStep(document.getProcessInstanceId(),
					users.getAccount());
			document.setStatus((String)os[0]);
			document.setProcessInstanceId((Long)os[1]);
			superDao.update(document);
		}
	}


	public List<ApproveHistory> searchPageApproveHistory(int index,String CompleteHql) {
		return objectToApproveHistory(superDao.getpage(index, CompleteHql));
	}
	public List<ApproveHistory> searchAllApproveHistory(String CompleteHql){
		return objectToApproveHistory(superDao.getAllObjects(CompleteHql));
	}
	private List<ApproveHistory> objectToApproveHistory(List<Object> lists){
		List<ApproveHistory> histories=new ArrayList<ApproveHistory>();
		for(Object object : lists){
			histories.add((ApproveHistory)object);
		}
		return histories;
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
