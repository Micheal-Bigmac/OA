package com.oa.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="oa_payPlan")
/**
 * 
 * @author Big mac
 *付款计划
 */
public class PayPlan {

	private Integer id;
	private PurchaseOrderRegisiter order;
	private String clientName;//客户名称
	private Date payRemind; // 付款提醒
	private Date payDate; //付款时间
	private Double price; // 金额
	private Boolean isPaid;
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
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public Date getPayRemind() {
		return payRemind;
	}
	public void setPayRemind(Date payRemind) {
		this.payRemind = payRemind;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Boolean getIsPaid() {
		return isPaid;
	}
	public void setIsPaid(Boolean isPaid) {
		this.isPaid = isPaid;
	}
}
