package com.oa.action;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.oa.model.Product;
import com.oa.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;

public class ProductAction extends ActionSupport {

	private Product product;
	private ProductService productService;
	private int index;
	private Integer pid;
	private String returns;

	public String productList() {
		String hql="";
		List<Product> Products = productService.getPageProducts((index == 0 ? 1
				: index), Product.class, hql);
		for (Product m : Products) {
			System.out.println(m.toString());
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("listObject", Products);
		request.setAttribute("currentIndex", (index == 0 ? 1 : index));
		int total = productService.getAllProducts(Product.class, hql).size();
		// request.setAttribute("pid",(Product==null ? "": product.getId()));
		request.setAttribute("totalSize", total);
		request.setAttribute("url", "ProductAction!productList");
		return "productList";
	}


	public String addproduct() {
		/*
		 * if(pid!=null){ product.setId(ProductService.getProduct(pid)); }
		 * returns="productAction!productList"; Serializable
		 * flag=ProductService.addproduct(product);
		 */
		Serializable flag = null;
		if (product.getId() == null) {
			flag = productService.addProduct(product);
			returns = "ProductAction!productList";
			return flag == null ? "operator_failure" : null;
		}
		productService.updateProduct(product);
		return "operator_success";
	}

	public String deleteproduct() {
		System.out.println("deleteperson");
		returns = "ProductAction!productList";
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] ids = request.getParameterValues("delid");
		System.out.println(ids.length + "sdfsadf");
		for (int i = 0; i < ids.length; i++) {
			System.out.println(ids[i]);
		}
		productService.deleteProducts(ids);
		returns = "ProductAction!productList";
		return "operator_success";
	}
	public String edit(){
		Product temp=productService.getProduct(product.getId());
		ServletActionContext.getRequest().setAttribute("product", temp);
		return "editView";
	}

	public ProductService getProductService() {
		return productService;
	}

	@Resource
	public void setProductService(ProductService productService) {
		this.productService = productService;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
