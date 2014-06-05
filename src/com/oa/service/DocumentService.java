package com.oa.service;

import java.io.Serializable;
import java.util.List;

import com.oa.model.Document;
import com.oa.model.DocumentProperty;

public interface DocumentService {

	/*
	 *  添加公文信息
	 * @param document 公文对象
	 * @param workflowId 公文对应的流程ID
	 * @param userId 公文的创建者ID
	 */
	public abstract void addDocument(Document document, int workflowId,
			int userId,List<DocumentProperty> props);

	/**
	 * 更新公文信息
	 * @param document
	 */
	public abstract void updateDocument(Document document, int workFLowId,
			int userId);

	/**
	 * 查找某个公文
	 * @param id
	 * @return
	 */
	public abstract Document findDocument(Serializable id);

	/**
	 * 查询(当前登录用户的)已审公文列表
	 * @param userId 用户ID，取当前登录用户的ID
	 * @return
	 */
	public abstract List<Document> searchAllApprovedDocuments(int userId);
	
	public List<Document> searchPageApprovedDocuments(int userId,int index);
	/**
	 * 查询待审（即等待当前登录用户审批的）公文列表
	 * @param userId 当前登录用户的ID
	 * @return
	 */
	public abstract List<Document> searchPageApprovingDocuments(String username,int index);
	
	public List<Document> SearchAllApprovingDocuments(String username);
	
	/**
	 * 
	 *  * 搜索我的公文列表（即搜索由当前登录用户创建的公文列表）
	 * @param clazz
	 * @param hql
	 * @return
	 */

	public List<Document> searchMyDocumentsPage(int index,int userId);
	
	public List<Document> searchAllMyDocument( int userId);
	
	/**
	 * 
	 * 归档文档
	 * @param index
	 * @param userId
	 * @return
	 */
	public List<Document> serachAllFinishDocuments( int userId);
	public List<Document> serachPageFinishDocuments(int index, int userId);
	/**
	 *  删除公文信息
	 * @param document
	 */
	public abstract void deleteDocument(Document document);
	
	public void deleteDocuments(String []ids);

	/**
	 * 
	 *搜索我的公文列表（即搜索由当前登录用户创建的公文列表）
	 * @param clazz
	 * @param hql
	 * @return
	 */
//	public abstract List<Document> getAllDocuments(Class clazz, String hql);

	/**
	 * 
	 * 分页搜索我的公文列表（即搜索由当前登录用户创建的公文列表）
	 * @param clazz
	 * @param hql
	 * @return
	 */
//	public abstract List<Document> getPageDocuments(int index, Class clazz,
//			String hql);

	
	
	/**
	 * 查询下一个可选步骤列表（公文ID，用户标识）
	 * @param documentId
	 * @param userId
	 * @return
	 */
	public List searchNextStep(int documentId,String userId);
	
	/**
	 * 提交到流程
	 * @param userId 当前登录用户的ID
	 * @param documentId 被提交的公文ID
	 * @param transistionName 要提交到哪里去
	 */
	public abstract void submitToWorkFlow(int userId, int documentId,
			String transistionName);

}