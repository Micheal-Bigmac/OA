package com.oa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="oa_performanceExamine")
public class PerformanceExamine {

	private Integer id;
	private Person personId;
	private PerformanceParameters paramId;
	private Integer score;
	private ListPerformanceExamine lpe;
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
	@OneToOne
	@JoinColumn(name="paramId")
	public PerformanceParameters getParamId() {
		return paramId;
	}
	public void setParamId(PerformanceParameters paramId) {
		this.paramId = paramId;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	@ManyToOne
	@JoinColumn(name="lpe")
	public ListPerformanceExamine getLpe() {
		return lpe;
	}
	public void setLpe(ListPerformanceExamine lpe) {
		this.lpe = lpe;
	}
	@Override
	public String toString() {
		return "PerformanceExamine [id=" + id + ", score=" + score + "]";
	}
	
}
