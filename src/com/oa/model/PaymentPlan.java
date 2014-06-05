package com.oa.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="oa_paymentPlan")
/**
 * 收款计划
 * @author Big mac
 *
 */
public class PaymentPlan {

	private Integer id;
	private SalesAgreement order;
	private String clientName;
	private Double price;//    saleAgreement.tatalprice-paidprice
	private Date gatheringRemind; // 收款提醒
	private Date toDate ;//到款日期
	private Boolean isPaid;
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public Date getGatheringRemind() {
		return gatheringRemind;
	}
	public void setGatheringRemind(Date gatheringRemind) {
		this.gatheringRemind = gatheringRemind;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Boolean getIsPaid() {
		return isPaid;
	}
	public void setIsPaid(Boolean isPaid) {
		this.isPaid = isPaid;
	}

	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@ManyToOne
	@JoinColumn(name="salesId",referencedColumnName="id")
	public SalesAgreement getOrder() {
		return order;
	}
	public void setOrder(SalesAgreement order) {
		this.order = order;
	}
	
}
