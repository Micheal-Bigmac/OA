package com.oa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="oa_rules")
public class Rules {
	private Integer id;
	private String captition;
	private String body;
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCaptition() {
		return captition;
	}
	public void setCaptition(String captition) {
		this.captition = captition;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "Rules [id=" + id + ", captition=" + captition + ", body="
				+ body + "]";
	}
}
