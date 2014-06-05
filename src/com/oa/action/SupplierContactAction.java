package com.oa.action;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.oa.model.SupplierContact;
import com.oa.model.SupplierManager;
import com.oa.service.SupplierContactService;
import com.oa.service.SupplierManagerService;
import com.opensymphony.xwork2.ActionSupport;

public class SupplierContactAction extends ActionSupport {

	private SupplierContact supplierContract;
	private SupplierContactService SupplierContactService;
	private SupplierManagerService supplierManagerService;
	private int index;
	private Integer pid;
	private String returns;

	public String SupplierContactList() {
		String hql="";
		List<SupplierContact> SupplierContacts = SupplierContactService.getPageSupplierContacts((index == 0 ? 1
				: index), SupplierContact.class, hql);
		for (SupplierContact m : SupplierContacts) {
			System.out.println(m.toString());
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("SupplierContactList", SupplierContacts);
		request.setAttribute("currentIndex", (index == 0 ? 1 : index));
		int total = SupplierContactService.getAllSupplierContacts(SupplierContact.class, hql).size();
		// request.setAttribute("pid",(SupplierContact==null ? "": SupplierContact.getId()));
		request.setAttribute("totalSize", total);
		getSelect();
		return "SupplierContactList";
	}
	private void getSelect(){
		List<SupplierManager> supplierManagers=supplierManagerService.getAllSupplierManagers(SupplierManager.class, "");
		HttpSession session=ServletActionContext.getRequest().getSession();
		session.setAttribute("supplierManagers", supplierManagers);
	}

	public String addSupplierContact() {
		/*
		 * if(pid!=null){ SupplierContact.setId(SupplierContactService.getSupplierContact(pid)); }
		 * returns="SupplierContactAction!SupplierContactList"; Serializable
		 * flag=SupplierContactService.addSupplierContact(SupplierContact);
		 */
		Serializable flag = null;
		if (supplierContract.getId() == null) {
			System.out.println(supplierContract.toString());
			flag = SupplierContactService.addSupplierContact(supplierContract);
			returns = "SupplierContractAction!SupplierContactList";
			return flag == null ? "operator_failure" : "operator_success";
		}
		returns = "SupplierContractAction!SupplierContactList";
		SupplierContactService.updateSupplierContacts(supplierContract);
		return "operator_success";
		// return "";
	}

	public String deleteSupplierContact() {
		System.out.println("deleteperson");
		returns = "SupplierContractAction!SupplierContactList";
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] ids = request.getParameterValues("delid");
		System.out.println(ids.length + "sdfsadf");
		for (int i = 0; i < ids.length; i++) {
			System.out.println(ids[i]);
		}
		SupplierContactService.deleteSupplierContacts(ids);
		return "operator_success";
	}
	public String edit(){
		SupplierContact temp=SupplierContactService.getSupplierContact(supplierContract.getId());
		ServletActionContext.getRequest().setAttribute("supplierContract", temp);
		return "editView";
	}

	public SupplierContactService getSupplierContactService() {
		return SupplierContactService;
	}

	@Resource
	public void setSupplierContactService(SupplierContactService SupplierContactService) {
		this.SupplierContactService = SupplierContactService;
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

	public SupplierContact getSupplierContact() {
		return supplierContract;
	}

	public void setSupplierContact(SupplierContact SupplierContact) {
		this.supplierContract = SupplierContact;
	}
	public SupplierManagerService getSupplierManagerService() {
		return supplierManagerService;
	}
	@Resource
	public void setSupplierManagerService(
			SupplierManagerService supplierManagerService) {
		this.supplierManagerService = supplierManagerService;
	}
	public SupplierContact getSupplierContract() {
		return supplierContract;
	}
	public void setSupplierContract(SupplierContact supplierContract) {
		this.supplierContract = supplierContract;
	}

}
