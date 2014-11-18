package com.oa.action;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.oa.listenner.Persistence;
import com.oa.model.Document;
import com.oa.model.PayPlan;
import com.oa.model.PurchaseOrderRegisiter;
import com.oa.model.Users;
import com.oa.service.DocumentService;
import com.oa.service.PayPlanService;
import com.oa.service.PurchaseOrderRegisiterService;
import com.oa.util.Constant;
import com.opensymphony.xwork2.ActionSupport;

public class PayPlanAction extends ActionSupport {

	private PayPlan payPlan;
	private PayPlanService PayPlanService;
	private PurchaseOrderRegisiterService purchaseOrderRegisiterService;
	private int index;
	private Integer pid;
	private String returns;
	private DocumentService documentService;
	private Integer workflowId;

	public String PayPlanList() {
		String hql = "";
		List<PayPlan> PayPlans = PayPlanService.getPagePayPlans((index == 0 ? 1 : index), PayPlan.class, hql);
		for (PayPlan m : PayPlans) {
			System.out.println(m.toString());
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("listObject", PayPlans);
		request.setAttribute("currentIndex", (index == 0 ? 1 : index));
		int total = PayPlanService.getAllPayPlans(PayPlan.class, hql).size();
		// request.setAttribute("pid",(PayPlan==null ? "": PayPlan.getId()));
		request.setAttribute("totalSize", total);
		request.setAttribute("url", "PayPlanAction!PayPlanList?");
		getSelect();
		return "PayPlanList";
	}

	private void getSelect() {
		List<PurchaseOrderRegisiter> PurchaseOrderRegisiter = purchaseOrderRegisiterService.getAllPurchaseOrderRegisiters(PurchaseOrderRegisiter.class, "");
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("purchaseOrderRegisiter", PurchaseOrderRegisiter);
	}

	public String addPayPlan() {
		
		Serializable flag = null;
		Users users = (Users) ServletActionContext.getRequest().getSession().getAttribute("admin");
		String key=null;
		if (payPlan.getId() == null) {
//			flag = PayPlanService.addPayPlan(payPlan);
			Document document = new Document();
			String temp=users.getAccount()+Constant.payPlan;
			document.setTitle(temp);
			document.setDescription(temp);
			key=Persistence.setVariable(payPlan);
			document.setTypePersist(key+"|payPlan");
			document.setUrl("showPayPlan.jsp");
			flag=documentService.addDocument(document, workflowId, users.getId(), null);
			returns = "PayPlanAction!PayPlanList";
			return flag == null ? "operator_failure" : "operator_success";
		}
		PayPlanService.updatePayPlans(payPlan);
		return "operator_success";
		// return "";
	}

	public String deletePayPlan() {
		System.out.println("deleteperson");
		returns = "PayPlanAction!PayPlanList";
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] ids = request.getParameterValues("delid");
		System.out.println(ids.length + "sdfsadf");
		for (int i = 0; i < ids.length; i++) {
			System.out.println(ids[i]);
		}
		PayPlanService.deletePayPlans(ids);
		returns = "PayPlanAction!PayPlanList";
		return "operator_success";
	}

	public String edit() {
		PayPlan temp = PayPlanService.getPayPlan(payPlan.getId());
		ServletActionContext.getRequest().setAttribute("payPlan", temp);
		return "editView";
	}

	public PayPlanService getPayPlanService() {
		return PayPlanService;
	}

	@Resource
	public void setPayPlanService(PayPlanService PayPlanService) {
		this.PayPlanService = PayPlanService;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getReturns() {
		return returns;
	}

	public void setReturns(String returns) {
		this.returns = returns;
	}

	public PayPlan getPayPlan() {
		return payPlan;
	}

	public void setPayPlan(PayPlan PayPlan) {
		this.payPlan = PayPlan;
	}

	public PurchaseOrderRegisiterService getPurchaseOrderRegisiterService() {
		return purchaseOrderRegisiterService;
	}

	@Resource
	public void setPurchaseOrderRegisiterService(PurchaseOrderRegisiterService PurchaseOrderRegisiterService) {
		this.purchaseOrderRegisiterService = PurchaseOrderRegisiterService;
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
