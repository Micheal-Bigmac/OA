package com.oa.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
//绩效参数
@Entity
@Table(name="oa_performanceParameters")
public class PerformanceParameters implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6045135713516739086L;
	private Integer id;
	private String name;
	private String date;
	private Person personId;
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
