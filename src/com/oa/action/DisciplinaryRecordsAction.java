package com.oa.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.oa.listenner.Persistence;
import com.oa.model.DisciplinaryRecords;
import com.oa.model.Document;
import com.oa.model.Users;
import com.oa.service.DisciplinaryRecordsSerivce;
import com.oa.service.DocumentService;
import com.oa.util.Constant;
import com.opensymphony.xwork2.ActionSupport;

public class DisciplinaryRecordsAction extends ActionSupport{

	
	private DisciplinaryRecordsSerivce disciplinaryRecordsService;
	private Integer disId;
	private DisciplinaryRecords disciplinaryRecords;
	private int index;
	
	private DocumentService documentService;
	private Integer workflowId;
	public String addDis() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Users user = (Users)session.getAttribute("admin");
		String key;
		
		if(disciplinaryRecords.getId() == null) {
			disciplinaryRecords.setPersonId(user.getPersonid());
//			disciplinaryRecordsService.addDis(disciplinaryRecords);
			Document document=new Document();
			String temp=user.getAccount()+Constant.disciplinaryRecords;
			document.setTitle(temp);
			document.setDescription(temp);
			key=Persistence.setVariable(disciplinaryRecords);
			document.setTypePersist(key+"|disciplinaryRecords");
			document.setUrl("showDisciplinaryRecords.jsp");
			documentService.addDocument(document, workflowId, user.getId(), null);
			System.out.println("add");
		} else {
			disciplinaryRecords.setPersonId(user.getPersonid());
			disciplinaryRecordsService.updateDis(disciplinaryRecords);
			System.out.println("update");
		}
		List<DisciplinaryRecords> listDis = disciplinaryRecordsService.selectDis();
		request.setAttribute("listDis", listDis);
		return "selectDis";
	}
	public String updateDis() {
		System.out.println("disId  is "+disId);
		DisciplinaryRecords dis = disciplinaryRecordsService.selectDis(DisciplinaryRecords.class,disId);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("disciplinaryRecords", dis);
		return "addDisciplinaryRecords";
	}
	public String deleteDis() {
		System.out.println("disId  is "+disId);
		DisciplinaryRecords dis = disciplinaryRecordsService.selectDis(DisciplinaryRecords.class,disId);
		disciplinaryRecordsService.deleteDis(dis);
		List<DisciplinaryRecords> listDis = disciplinaryRecordsService.selectDis();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("listDis", listDis);
		return "operator_success";
	}
	
	public String find(){
		String hql = "";
		List<DisciplinaryRecords> disciplinaryRecords = disciplinaryRecordsService.getDisciplinaryRecordsPages((index==0 ? 1 : index), DisciplinaryRecords.class, hql);
		int total = disciplinaryRecordsService.getAllDisciplinaryRecords(DisciplinaryRecords.class, hql).size();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("listObject", disciplinaryRecords);
		request.setAttribute("currentIndex", (index==0 ?  1 : index));
		request.setAttribute("totalSize",total);
		request.setAttribute("url", "DisciplinaryRecordsAction!find?");
		return "selectDis";
	}
	public DisciplinaryRecordsSerivce getDisciplinaryRecordsService() {
		return disciplinaryRecordsService;
	}
	@Resource
	public void setDisciplinaryRecordsService(DisciplinaryRecordsSerivce disciplinaryRecordsService) {
		this.disciplinaryRecordsService = disciplinaryRecordsService;
	}
	
	public DisciplinaryRecords getDisciplinaryRecords() {
		return disciplinaryRecords;
	}
	public void setDisciplinaryRecords(DisciplinaryRecords disciplinaryRecords) {
		this.disciplinaryRecords = disciplinaryRecords;
	}
	public Integer getDisId() {
		return disId;
	}
	public void setDisId(Integer disId) {
		this.disId = disId;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public DocumentService getDocumentService() {
		return documentService;
	}
	@Resource
	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}
	public Integer getWorkflowId() {
		return workflowId;
	}
	public void setWorkflowId(Integer workflowId) {
		this.workflowId = workflowId;
	}
}
