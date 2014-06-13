package com.oa.action;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.oa.model.ContractProductRecord;
import com.oa.model.Product;
import com.oa.model.SalesAgreement;
import com.oa.service.ContractProductRecordService;
import com.oa.service.ProductService;
import com.oa.service.SalesAgreementService;
import com.opensymphony.xwork2.ActionSupport;

public class ContractProductAction extends ActionSupport {

	private ContractProductRecord contractProductRecord;
	private ContractProductRecordService contractProductRecordService;
	private SalesAgreementService salesAgreementService;
	private ProductService productService;
	private int index;
	private Integer pid;
	private String returns;

	public String ContractProductRecordList() {
		String hql="";
		List<ContractProductRecord> ContractProductRecords = contractProductRecordService.getpageContractProductRecords((index == 0 ? 1
				: index), ContractProductRecord.class, hql);
		for (ContractProductRecord m : ContractProductRecords) {
			System.out.println(m.toString());
		}
		getSelect();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("listObject", ContractProductRecords);
		request.setAttribute("currentIndex", (index == 0 ? 1 : index));
		int total = contractProductRecordService.getAllContractProductRecords(ContractProductRecord.class, hql).size();
		// request.setAttribute("pid",(ContractProductRecord==null ? "": ContractProductRecord.getId()));
		request.setAttribute("totalSize", total);
		request.setAttribute("url", "ContractProductAction!ContractProductRecordList?");
		return "ContractProductRecordList";
	}


	public String addContractProduct() {
	
		Serializable flag = null;
		if (contractProductRecord.getId() == null) {
			flag = contractProductRecordService.addContractProductRecord(contractProductRecord);
			returns = "ContractProductAction!ContractProductRecordList";
			return flag == null ? "operator_failure" : "operator_success";
		}
		contractProductRecordService.updateContractProductRecords(contractProductRecord);
		returns = "ContractProductAction!ContractProductRecordList";
		return "operator_success";
		// return "";
	}


	private void getSelect() {
		List<Product> products=productService.getAllProducts(Product.class, "");
		List<SalesAgreement> salesAgreements=salesAgreementService.getAllsalesAgreements(SalesAgreement.class, "");
		HttpSession session=ServletActionContext.getRequest().getSession();
		session.setAttribute("products", products);
		session.setAttribute("salesAgreements", salesAgreements);
	}

	public String deleteContractProductRecord() {
		System.out.println("deleteperson");
		returns = "ContractProductRecordAction!ContractProductRecordList";
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] ids = request.getParameterValues("delid");
		System.out.println(ids.length + "sdfsadf");
		for (int i = 0; i < ids.length; i++) {
			System.out.println(ids[i]);
		}
		contractProductRecordService.deletegetpageContractProductRecords(ids);
		returns = "ContractProductAction!ContractProductRecordList";
		return "operator_success";
	}
	public String edit(){
		ContractProductRecord temp=contractProductRecordService.getContractProductRecord(contractProductRecord.getId());
		ServletActionContext.getRequest().setAttribute("contractProductRecord", temp);
		return "editView";
	}

	public ContractProductRecordService getContractProductRecordService() {
		return contractProductRecordService;
	}

	@Resource
	public void setContractProductRecordService(ContractProductRecordService ContractProductRecordService) {
		this.contractProductRecordService = ContractProductRecordService;
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

	public ContractProductRecord getContractProductRecord() {
		return contractProductRecord;
	}

	public void setContractProductRecord(ContractProductRecord ContractProductRecord) {
		this.contractProductRecord = ContractProductRecord;
	}

	

	public SalesAgreementService getSalesAgreementService() {
		return salesAgreementService;
	}
	@Resource
	public void setSalesAgreementService(SalesAgreementService salesAgreementService) {
		this.salesAgreementService = salesAgreementService;
	}
	
	
	public ProductService getProductService() {
		return productService;
	}
	@Resource
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

}
