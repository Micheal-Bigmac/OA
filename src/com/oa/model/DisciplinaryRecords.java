package com.oa.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;
//奖惩制度
@Entity
@Table(name="oa_disciplinaryRecords")
public class DisciplinaryRecords implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8792456437190262046L;
	private Integer id;
	private Person personId;
	private String disciplinaryDistringuish;
	private String disciplinaryResult;
	private String awardUnit;
	private Date disciplinaryDate;
	private String disciplinaryItem;
	private String disciplinaryReason;
	private String remark;
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@OneToOne
	@JoinColumn(name="personId")
	public Person getPersonId() {
		return personId;
	}
	public void setPersonId(Person personId) {
		this.personId = personId;
	}
	public String getDisciplinaryDistringuish() {
		return disciplinaryDistringuish;
	}
	public void setDisciplinaryDistringuish(String disciplinaryDistringuish) {
		this.disciplinaryDistringuish = disciplinaryDistringuish;
	}
	public String getDisciplinaryResult() {
		return disciplinaryResult;
	}
	public void setDisciplinaryResult(String disciplinaryResult) {
		this.disciplinaryResult = disciplinaryResult;
	}
	public String getAwardUnit() {
		return awardUnit;
	}
	public void setAwardUnit(String awardUnit) {
		this.awardUnit = awardUnit;
	}
	
	public String getDisciplinaryItem() {
		return disciplinaryItem;
	}
	public void setDisciplinaryItem(String disciplinaryItem) {
		this.disciplinaryItem = disciplinaryItem;
	}
	public String getDisciplinaryReason() {
		return disciplinaryReason;
	}
	public void setDisciplinaryReason(String disciplinaryReason) {
		this.disciplinaryReason = disciplinaryReason;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Temporal(TemporalType.DATE)
	public Date getDisciplinaryDate() {
		return disciplinaryDate;
	}
	public void setDisciplinaryDate(Date disciplinaryDate) {
		this.disciplinaryDate = disciplinaryDate;
	}
}
