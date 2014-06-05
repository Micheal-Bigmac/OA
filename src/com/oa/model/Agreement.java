package com.oa.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="oa_agreement")
public class Agreement implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5148803226789657131L;
	private Integer id;
	private Person personId;
	private String aggState;
	private String aggId;
	private String aggType;
	private String competivieArtical;
	private String secretProtocal;
	private Date signContractDate;
	private Date fullAboutDate;
	private String identifyOffice;
	private Date identifyDate;
	private String others;
	public String getAggState() {
		return aggState;
	}
	public void setAggState(String aggState) {
		this.aggState = aggState;
	}
	public String getAggType() {
		return aggType;
	}
	public void setAggType(String aggType) {
		this.aggType = aggType;
	}
	public String getCompetivieArtical() {
		return competivieArtical;
	}
	public void setCompetivieArtical(String competivieArtical) {
		this.competivieArtical = competivieArtical;
	}
	public String getSecretProtocal() {
		return secretProtocal;
	}
	public void setSecretProtocal(String secretProtocal) {
		this.secretProtocal = secretProtocal;
	}
	@Temporal(TemporalType.DATE)
	public Date getSignContractDate() {
		return signContractDate;
	}
	public void setSignContractDate(Date signContractDate) {
		this.signContractDate = signContractDate;
	}
	@Temporal(TemporalType.DATE)
	public Date getFullAboutDate() {
		return fullAboutDate;
	}
	public void setFullAboutDate(Date fullAboutDate) {
		this.fullAboutDate = fullAboutDate;
	}
	public String getIdentifyOffice() {
		return identifyOffice;
	}
	public void setIdentifyOffice(String identifyOffice) {
		this.identifyOffice = identifyOffice;
	}
	@Temporal(TemporalType.DATE)
	public Date getIdentifyDate() {
		return identifyDate;
	}
	public void setIdentifyDate(Date identifyDate) {
		this.identifyDate = identifyDate;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	public String getAggId() {
		return aggId;
	}
	public void setAggId(String aggId) {
		this.aggId = aggId;
	}
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
}
