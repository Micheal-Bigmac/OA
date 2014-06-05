package com.oa.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="oa_onandoffregister")
public class OnAndOffRegister implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7007239195931818117L;
	private Integer id;
	private String regularTime;
	private String state;
	
	@GeneratedValue
	@Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getRegularTime() {
		return regularTime;
	}
	public void setRegularTime(String regularTime) {
		this.regularTime = regularTime;
	}
	
	
}
