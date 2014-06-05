package com.oa.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 
 * 
 * @author Big mac
 *
 */

@Entity
@Table(name="oa_contractProductRecord")
/**
 * 合同产品记录
 * 
 * @author Big mac
 *
 */
public class ContractProductRecord implements Serializable{

	private Integer id;
	private Integer number;
	/////
	private Product product;
	private SalesAgreement agreement;
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	@ManyToOne
	@JoinColumn(name="productId",referencedColumnName="id")
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@ManyToOne
	@JoinColumn(name="agreementId",referencedColumnName="id")
	public SalesAgreement getAgreement() {
		return agreement;
	}
	public void setAgreement(SalesAgreement agreement) {
		this.agreement = agreement;
	}
}
