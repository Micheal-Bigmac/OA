package com.oa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.persistence.Entity;
@Entity
@Table(name="oa_purchaseorderRegisiter")
/**
 * 
 * @author Big mac
 *采购订单登记
 *
 */
public class PurchaseOrderRegisiter implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1378232318785944790L;
	private Integer id;
	private String orderName;
	private String orderCode;
	private String orderType; //订单类型
	private String provider; //供应商
	private String enterPerson;   ///录入人
	private Date enterDate;
	private String currentState;
	private Double tatalPrice;
	private Double paidPrice;
	private Double loanPrice; // 尚欠金额
	private Boolean isPaid;
	
	private Set<OrderProductRecord> productRecords=new HashSet<OrderProductRecord>();
	private Set<PayPlan> payPlans=new HashSet<PayPlan>();
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getEnterPerson() {
		return enterPerson;
	}
	public void setEnterPerson(String enterPerson) {
		this.enterPerson = enterPerson;
	}
	public Date getEnterDate() {
		return enterDate;
	}
	public void setEnterDate(Date enterDate) {
		this.enterDate = enterDate;
	}
	public Double getTatalPrice() {
		return tatalPrice;
	}
	public void setTatalPrice(Double tatalPrice) {
		this.tatalPrice = tatalPrice;
	}
	public Double getPaidPrice() {
		return paidPrice;
	}
	public void setPaidPrice(Double paidPrice) {
		this.paidPrice = paidPrice;
	}
	public Double getLoanPrice() {
		return loanPrice;
	}
	public void setLoanPrice(Double loanPrice) {
		this.loanPrice = loanPrice;
	}
	public Boolean getIsPaid() {
		return isPaid;
	}
	public void setIsPaid(Boolean isPaid) {
		this.isPaid = isPaid;
	}
	public String getCurrentState() {
		return currentState;
	}
	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	@OneToMany(mappedBy="order",cascade={CascadeType.ALL})
	public Set<OrderProductRecord> getProductRecords() {
		return productRecords;
	}
	public void setProductRecords(Set<OrderProductRecord> productRecords) {
		this.productRecords = productRecords;
	}
	@OneToMany(mappedBy="order",cascade={CascadeType.ALL})
	public Set<PayPlan> getPayPlans() {
		return payPlans;
	}
	public void setPayPlans(Set<PayPlan> payPlans) {
		this.payPlans = payPlans;
	}
}
