package com.oa.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.oa.listenner.Persistence;
import com.oa.model.Agreement;
import com.oa.model.Agreement;
import com.oa.model.Document;
import com.oa.model.Users;
import com.oa.service.AgreementSerivce;
import com.oa.service.DocumentService;
import com.oa.util.Constant;
import com.opensymphony.xwork2.ActionSupport;

public class AgreementAction extends ActionSupport{

	private AgreementSerivce agreementService;
	private Integer agreeId;
	private Agreement agreement;
	private int index;
	
	private DocumentService documentService;
	private Integer workflowId;
	public String addAgree() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Users user = (Users)session.getAttribute("admin");
		String key;
		
		if(agreement.getId() == null) {
			agreement.setPersonId(user.getPersonid());
			Document document=new Document();
			String temp=user.getAccount()+Constant.agreement;
			document.setTitle(temp);
			document.setDescription(temp);
			key=Persistence.setVariable(agreement);
			document.setTypePersist(key+"|agreement");
			document.setUrl("showAgreement.jsp");
			documentService.addDocument(document, workflowId, user.getId(), null);
//			agreementService.addAgree(agreement);
			System.out.println("add");
		} else {
			agreement.setPersonId(user.getPersonid());
			agreementService.updateAgree(agreement);
			System.out.println("update");
		}
		String hql = "";
		List<Agreement> agreements = agreementService.getAgreementPages((index==0 ? 1 : index), Agreement.class, hql);
		int total = agreementService.getAllAgreements(Agreement.class, hql).size();
		
		request.setAttribute("listAgree", agreements);
		request.setAttribute("currentIndex", (index==0 ?  1 : index ));
		request.setAttribute("totalSize",total);
		
		return "selectAgree";
	}
	public String updateAgree() {
		System.out.println("agreeId  is "+agreeId);
		Agreement agree = agreementService.selectAgree(Agreement.class,agreeId);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("agreement", agree);
		return "addAgreement";
	}
	public String deleteAgree() {
		System.out.println("agreeId  is "+agreeId);
		Agreement agree = agreementService.selectAgree(Agreement.class,agreeId);
		agreementService.deleteAgree(agree);
		
		String hql = "";
		List<Agreement> agreements = agreementService.getAgreementPages((index==0 ? 1 : index), Agreement.class, hql);
		int total = agreementService.getAllAgreements(Agreement.class, hql).size();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("listAgree", agreements);
		request.setAttribute("currentIndex", (index==0 ?  1 : index ));
		request.setAttribute("totalSize",total);
		
		return "operator_success";
	}
	
	public String find(){
		String hql = "";
		List<Agreement> agreements = agreementService.getAgreementPages((index==0 ? 1 : index), Agreement.class, hql);
		int total = agreementService.getAllAgreements(Agreement.class, hql).size();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("listObject", agreements);
		request.setAttribute("currentIndex", (index==0 ?  1 : index ));
		request.setAttribute("totalSize",total);
		request.setAttribute("url", "AgreementAction!find?");
		return "selectAgree";
	}
	public AgreementSerivce getAgreementService() {
		return agreementService;
	}
	@Resource
	public void setAgreementService(AgreementSerivce agreementService) {
		this.agreementService = agreementService;
	}
	
	public Agreement getAgreement() {
		return agreement;
	}
	public void setAgreement(Agreement agreement) {
		this.agreement = agreement;
	}
	public Integer getAgreeId() {
		return agreeId;
	}
	public void setAgreeId(Integer agreeId) {
		this.agreeId = agreeId;
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
