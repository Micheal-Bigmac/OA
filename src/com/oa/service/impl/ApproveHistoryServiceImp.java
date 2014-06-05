package com.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.oa.dao.ApproveHistoryDao;
import com.oa.model.ApproveHistory;
import com.oa.service.ApproveHistoryService;
@Component("approveHistoryService")
public class ApproveHistoryServiceImp implements ApproveHistoryService {
	private ApproveHistoryDao approveHistoryDao;
	
	/* (non-Javadoc)
	 * @see com.oa.service.impl.ApproveHistoryService#addApproveHistory(com.oa.model.ApproveHistory, int, int, boolean)
	 */
	public void addApproveHistory(ApproveHistory approveInfo,int documentId,int approverId,boolean back){
		approveHistoryDao.addApproveHistory(approveInfo, documentId, approverId, back);
	}
	
	/* (non-Javadoc)
	 * @see com.oa.service.impl.ApproveHistoryService#searchApproveHistory(int)
	 */
	public List<ApproveHistory> searchPageApproveHistory(int index,int documentId){
		String CompleteHql="from ApproveHistory h where h.document.id = "+documentId;
		return approveHistoryDao.searchPageApproveHistory(index, CompleteHql);
	}
	
	public List<ApproveHistory> searchAllApproveHistory(int documentId){
		String CompleteHql="from ApproveHistory h where h.document.id = "+documentId;
		return approveHistoryDao.searchAllApproveHistory(CompleteHql);
	}



	public ApproveHistoryDao getApproveHistoryDao() {
		return approveHistoryDao;
	}



	@Resource
	public void setApproveHistoryDao(ApproveHistoryDao approveHistoryDao) {
		this.approveHistoryDao = approveHistoryDao;
	}

}
