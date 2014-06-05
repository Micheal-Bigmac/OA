package com.oa.action;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.oa.model.SupplierManager;
import com.oa.service.SupplierManagerService;
import com.opensymphony.xwork2.ActionSupport;

public class SupplierManagerAction extends ActionSupport {

	private SupplierManager supplierManager;
	private SupplierManagerService SupplierManagerService;
	private int index;
	private Integer pid;
	private String returns;

	public String SupplierManagerList() {
		String hql="";
		List<SupplierManager> SupplierManagers = SupplierManagerService.getPageSupplierManagers((index == 0 ? 1
				: index), SupplierManager.class, hql);
		for (SupplierManager m : SupplierManagers) {
			System.out.println(m.toString());
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("SupplierManagerList", SupplierManagers);
		request.setAttribute("currentIndex", (index == 0 ? 1 : index));
		int total = SupplierManagerService.getAllSupplierManagers(SupplierManager.class, hql).size();
		// request.setAttribute("pid",(SupplierManager==null ? "": SupplierManager.getId()));
		request.setAttribute("totalSize", total);
		return "SupplierManagerList";
	}

	public String addSupplierManager() {
		/*
		 * if(pid!=null){ SupplierManager.setId(SupplierManagerService.getSupplierManager(pid)); }
		 * returns="SupplierManagerAction!SupplierManagerList"; Serializable
		 * flag=SupplierManagerService.addSupplierManager(SupplierManager);
		 */
		Serializable flag = null;
		System.out.println(supplierManager);
		if (supplierManager.getId() == null) {
			flag = SupplierManagerService.addSupplierManager(supplierManager);
			returns = "SupplierManagerAction!SupplierManagerList";
			return flag == null ? "operator_failure" : "operator_success";
		}
		SupplierManagerService.updateSupplierManager(supplierManager);
		return "operator_success";
		// return "";
	}

	public String deletesupplierManager() {
		System.out.println("deleteperson");
		returns = "SupplierManagerAction!SupplierManagerList";
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] ids = request.getParameterValues("delid");
		System.out.println(ids.length + "sdfsadf");
		for (int i = 0; i < ids.length; i++) {
			System.out.println(ids[i]);
		}
		SupplierManagerService.deleteSupplierManagers(ids);
		returns = "SupplierManagerAction!SupplierManagerList";
		return "operator_success";
	}
	public String edit(){
		SupplierManager temp=SupplierManagerService.getSupplierManager(supplierManager.getId());
		ServletActionContext.getRequest().setAttribute("supplierManager", temp);
		return "editView";
	}

	public SupplierManagerService getSupplierManagerService() {
		return SupplierManagerService;
	}

	@Resource
	public void setSupplierManagerService(SupplierManagerService SupplierManagerService) {
		this.SupplierManagerService = SupplierManagerService;
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

	public SupplierManager getSupplierManager() {
		return supplierManager;
	}

	public void setSupplierManager(SupplierManager supplierManager) {
		this.supplierManager = supplierManager;
	}

}
