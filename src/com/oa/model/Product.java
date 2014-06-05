package com.oa.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="oa_product")
/**
 *产品管理 
 * 
 * @author Big mac
 *
 */
public class Product implements Serializable{

	private Integer id;
	private String productName;
	private String type;
	private String productCode;
	private Double inputPrise;
	private Double outputPrise;
	private Integer totalOutbound;// 出库总量
	private Integer totalInventory;  // 库存总量
	private Integer currentInventory;// 当前总量
	
	private Set<ContractProductRecord> contractProductRecords=new HashSet<ContractProductRecord>();
	private Set<OrderProductRecord> orderProductRecords=new HashSet<OrderProductRecord>();
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public Double getInputPrise() {
		return inputPrise;
	}
	public void setInputPrise(Double inputPrise) {
		this.inputPrise = inputPrise;
	}
	public Double getOutputPrise() {
		return outputPrise;
	}
	public void setOutputPrise(Double outputPrise) {
		this.outputPrise = outputPrise;
	}

	public Integer getTotalInventory() {
		return totalInventory;
	}
	public void setTotalInventory(Integer totalInventory) {
		this.totalInventory = totalInventory;
	}
	public Integer getCurrentInventory() {
		return currentInventory;
	}
	public void setCurrentInventory(Integer currentInventory) {
		this.currentInventory = currentInventory;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@OneToMany(mappedBy="product",cascade={CascadeType.ALL})
	public Set<ContractProductRecord> getContractProductRecords() {
		return contractProductRecords;
	}
	public void setContractProductRecords(
			Set<ContractProductRecord> contractProductRecords) {
		this.contractProductRecords = contractProductRecords;
	}
	@OneToMany(mappedBy="product",cascade={CascadeType.ALL})
	public Set<OrderProductRecord> getOrderProductRecords() {
		return orderProductRecords;
	}
	public void setOrderProductRecords(Set<OrderProductRecord> orderProductRecords) {
		this.orderProductRecords = orderProductRecords;
	}
	public Integer getTotalOutbound() {
		return totalOutbound;
	}
	public void setTotalOutbound(Integer totalOutbound) {
		this.totalOutbound = totalOutbound;
	}
}
