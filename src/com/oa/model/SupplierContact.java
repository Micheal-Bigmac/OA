package com.oa.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="oa_supplierContact")

/**
 * 
 * @author Big mac
 *供应商联系人
 */

public class SupplierContact implements Serializable{

	private Integer id;
	private SupplierManager supplier;
	private String contactName;
	private String position;
	private String gender;
	private String phone;
	private String telephone;
	private String QQ;
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name="supplierId",referencedColumnName="id")
	public SupplierManager getSupplier() {
		return supplier;
	}
	public void setSupplier(SupplierManager supplier) {
		this.supplier = supplier;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getQQ() {
		return QQ;
	}
	public void setQQ(String qQ) {
		QQ = qQ;
	}
	@Override
	public String toString() {
		return "SupplierContact [id=" + id + ", contactName=" + contactName
				+ ", position=" + position + ", gender=" + gender + ", phone="
				+ phone + ", telephone=" + telephone + ", QQ=" + QQ + "]";
	}
}
