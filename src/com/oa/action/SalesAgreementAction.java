package com.oa.action;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.oa.model.SalesAgreement;
import com.oa.model.Users;
import com.oa.service.SalesAgreementService;
import com.opensymphony.xwork2.ActionSupport;

public class SalesAgreementAction extends ActionSupport {

	private SalesAgreement salesAgreement;
	private SalesAgreementService salesAgreementService;
	private int index;
	private Integer pid;
	private String returns;

	public String SalesAgreementList() {
		String hql="";
		List<SalesAgreement> SalesAgreements = salesAgreementService.getpageAgreements((index == 0 ? 1
				: index), SalesAgreement.class, hql);
		for (SalesAgreement m : SalesAgreements) {
			System.out.println(m.toString());
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("listObject", SalesAgreements);
		request.setAttribute("currentIndex", (index == 0 ? 1 : index));
		int total = salesAgreementService.getAllsalesAgreements(SalesAgreement.class, hql).size();
		// request.setAttribute("pid",(SalesAgreement==null ? "": SalesAgreement.getId()));
		request.setAttribute("totalSize", total);
		request.setAttribute("url", "SalesAgreementAction!SalesAgreementList");
		return "SalesAgreementList";
	}


	public String addSalesAgreement() {
		/*
		 * if(pid!=null){ SalesAgreement.setId(SalesAgreementService.getSalesAgreement(pid)); }
		 * returns="SalesAgreementAction!SalesAgreementList"; Serializable
		 * flag=SalesAgreementService.addSalesAgreement(SalesAgreement);
		 */
		Serializable flag = null;
		if (salesAgreement.getId() == null) {
//			System.out.println(salesAgreement.toString());
			salesAgreement.setEnterDate(new Date());
			Users users=(Users) ServletActionContext.getRequest().getSession().getAttribute("admin");
			salesAgreement.setEnterPerson(users.getPersonid().getName());
			flag = salesAgreementService.addSalesAgreement(salesAgreement);
			returns = "SalesAgreementAction!SalesAgreementList";
			return flag == null ? "operator_failure" : "operator_success";
		}
		salesAgreementService.updateSalesAgreement(salesAgreement);
		returns = "SalesAgreementAction!SalesAgreementList";
		return "operator_success";
		// return "";
	}

	public String deleteSalesAgreement() {
		System.out.println("deleteperson");
		returns = "SalesAgreementAction!SalesAgreementList";
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] ids = request.getParameterValues("delid");
		System.out.println(ids.length + "sdfsadf");
		for (int i = 0; i < ids.length; i++) {
			System.out.println(ids[i]);
		}
		salesAgreementService.deleteSalesAgreements(ids);
		returns = "SalesAgreementAction!SalesAgreementList";
		return "operator_success";
	}
	public String edit(){
		SalesAgreement temp=salesAgreementService.getSalesAgreement(salesAgreement.getId());
		ServletActionContext.getRequest().setAttribute("salesAgreement", temp);
		return "editView";
	}

	public SalesAgreementService getSalesAgreementService() {
		return salesAgreementService;
	}

	@Resource
	public void setSalesAgreementService(SalesAgreementService SalesAgreementService) {
		this.salesAgreementService = SalesAgreementService;
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

	public SalesAgreement getSalesAgreement() {
		return salesAgreement;
	}

	public void setSalesAgreement(SalesAgreement SalesAgreement) {
		this.salesAgreement = SalesAgreement;
	}

}
