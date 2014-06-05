package com.oa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="oa_salesAgreement")

/**
 * 
 * 销售合同登记
 * @author Big mac
 *
 */

public class SalesAgreement implements Serializable {

	private Integer id;
	private String salesName;
	private String salesCode;
	private String contractTitle; //合同类别
	private String contractName;  // 签约客户
	private String enterPerson;   ///录入人
	private Date enterDate;
	private String currentStatus;
	private Double tatalPrice;
	private Double paidPrice;
	private Double loanPrice; // 尚欠金额
	private Boolean isPaid;
	
	private Set<PaymentPlan> paymentPlans=new HashSet<PaymentPlan>();
	private Set<ContractProductRecord> contractProductRecords=new HashSet<ContractProductRecord>();
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSalesName() {
		return salesName;
	}
	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}
	public String getSalesCode() {
		return salesCode;
	}
	public void setSalesCode(String salesCode) {
		this.salesCode = salesCode;
	}
	public String getContractTitle() {
		return contractTitle;
	}
	public void setContractTitle(String contractTitle) {
		this.contractTitle = contractTitle;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
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
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	@Override
	public String toString() {
		return "SalesAgreement [id=" + id + ", salesName=" + salesName
				+ ", salesCode=" + salesCode + ", contractTitle="
				+ contractTitle + ", contractName=" + contractName
				+ ", enterPerson=" + enterPerson + ", enterDate=" + enterDate
				+ ", currentStatus=" + currentStatus + ", tatalPrice="
				+ tatalPrice + ", paidPrice=" + paidPrice + ", loanPrice="
				+ loanPrice + ", isPaid=" + isPaid + "]";
	}
	@OneToMany(mappedBy="order",cascade={CascadeType.ALL})
	public Set<PaymentPlan> getPaymentPlans() {
		return paymentPlans;
	}
	public void setPaymentPlans(Set<PaymentPlan> paymentPlans) {
		this.paymentPlans = paymentPlans;
	}
	@OneToMany(mappedBy="agreement",cascade={CascadeType.ALL})
	public Set<ContractProductRecord> getContractProductRecords() {
		return contractProductRecords;
	}
	public void setContractProductRecords(
			Set<ContractProductRecord> contractProductRecords) {
		this.contractProductRecords = contractProductRecords;
	}

}
