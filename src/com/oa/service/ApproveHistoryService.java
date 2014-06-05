package com.oa.service;

import java.util.List;

import com.oa.model.ApproveHistory;

public interface ApproveHistoryService {

	/**
	 * 审批公文，记录审批信息
	 * @param approveInfo 审批信息
	 * @param documentId 被审批的公文
	 * @param approverId 审批者，取当前登录用户的ID
	 * @param back 是否回退
	 */
	public abstract void addApproveHistory(ApproveHistory approveInfo,
			int documentId, int approverId, boolean back);

	/**
	 * 查询公文的审批历史（即查询公文都经过了哪些人审批）
	 * @param documentId 公文的ID
	 * @return
	 */
	public abstract List<ApproveHistory> searchPageApproveHistory(int index,int documentId);

	public List<ApproveHistory> searchAllApproveHistory(int documentId);
}