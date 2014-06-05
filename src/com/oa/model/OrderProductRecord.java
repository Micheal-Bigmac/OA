package com.oa.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="oa_orderProductRecord")
/***
 * 
 * @author Big mac
 *订单产品记录
 */
public class OrderProductRecord implements Serializable {
	private Integer id;
	private PurchaseOrderRegisiter order;
	private Product product;
	
	private Integer number ;

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="orderId",referencedColumnName="id")
	public PurchaseOrderRegisiter getOrder() {
		return order;
	}

	public void setOrder(PurchaseOrderRegisiter order) {
		this.order = order;
	}

	@ManyToOne
	@JoinColumn(name="productId",referencedColumnName="id")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
}
