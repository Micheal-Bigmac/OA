package com.oa.action;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.oa.model.OrderProductRecord;
import com.oa.model.Product;
import com.oa.model.PurchaseOrderRegisiter;
import com.oa.service.OrderProductRecordService;
import com.oa.service.ProductService;
import com.oa.service.PurchaseOrderRegisiterService;
import com.opensymphony.xwork2.ActionSupport;

public class OrderProductRecordAction extends ActionSupport {

	private OrderProductRecord orderProductRecord;
	private OrderProductRecordService orderProductRecordService;
	private ProductService productService;
	private PurchaseOrderRegisiterService purchaseOrderRegisiterService;
	private int index;
	private Integer pid;
	private String returns;

	public String OrderProductRecordList() {
		String hql="";
		List<OrderProductRecord> OrderProductRecords = orderProductRecordService.getPageOrderProductRecords((index == 0 ? 1
				: index), OrderProductRecord.class, hql);
		for (OrderProductRecord m : OrderProductRecords) {
			System.out.println(m.toString());
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("orderProductRecordList", OrderProductRecords);
		request.setAttribute("currentIndex", (index == 0 ? 1 : index));
		
		List<Product> products=productService.getAllProducts(Product.class, "");
		List<PurchaseOrderRegisiter> purchaseOrderRegisiter=purchaseOrderRegisiterService.getAllPurchaseOrderRegisiters(PurchaseOrderRegisiter.class, hql);
		HttpSession session=ServletActionContext.getRequest().getSession();
		session.setAttribute("products", products);
		session.setAttribute("purchaseOrderRegisiter", purchaseOrderRegisiter);
		
		int total = orderProductRecordService.getAllOrderProductRecords(OrderProductRecord.class, hql).size();
		System.out.println("=============================="+total);
		// request.setAttribute("pid",(OrderProductRecord==null ? "": OrderProductRecord.getId()));
		request.setAttribute("totalSize", total);
		return "OrderProductRecordList";
	}
	

	public String addOrderProductRecord() {
		
		Serializable flag = null;
		if (orderProductRecord.getId() == null) {
			flag = orderProductRecordService.addOrderProductRecord(orderProductRecord);
			returns = "OrderProductRecordAction!OrderProductRecordList";
			return flag == null ? "operator_failure" : "operator_success";
		}
		orderProductRecordService.updateOrderProductRecord(orderProductRecord);
		returns = "OrderProductRecordAction!OrderProductRecordList";
		return "operator_success";
		// return "";
	}

	public String deleteOrderProductRecord() {
		System.out.println("deleteperson");
		returns = "OrderProductRecordAction!OrderProductRecordList";
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] ids = request.getParameterValues("delid");
		System.out.println(ids.length + "sdfsadf");
		for (int i = 0; i < ids.length; i++) {
			System.out.println(ids[i]);
		}
		orderProductRecordService.deleteOrderProductRecords(ids);
		returns = "OrderProductRecordAction!OrderProductRecordList";
		return "operator_success";
	}
	public String edit(){
		OrderProductRecord temp=orderProductRecordService.getOrderProductRecord(orderProductRecord.getId());
		ServletActionContext.getRequest().setAttribute("orderProductRecord", temp);
		return "editView";
	}

	public OrderProductRecordService getOrderProductRecordService() {
		return orderProductRecordService;
	}

	@Resource
	public void setOrderProductRecordService(OrderProductRecordService OrderProductRecordService) {
		this.orderProductRecordService = OrderProductRecordService;
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

	public OrderProductRecord getOrderProductRecord() {
		return orderProductRecord;
	}

	public void setOrderProductRecord(OrderProductRecord OrderProductRecord) {
		this.orderProductRecord = OrderProductRecord;
	}

	public ProductService getProductService() {
		return productService;
	}


	@Resource
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}


	public PurchaseOrderRegisiterService getPurchaseOrderRegisiterService() {
		return purchaseOrderRegisiterService;
	}


	@Resource
	public void setPurchaseOrderRegisiterService(
			PurchaseOrderRegisiterService purchaseOrderRegisiterService) {
		this.purchaseOrderRegisiterService = purchaseOrderRegisiterService;
	}

}
