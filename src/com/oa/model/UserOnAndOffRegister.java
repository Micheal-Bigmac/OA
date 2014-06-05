package com.oa.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="oa_useronandoffregister")
public class UserOnAndOffRegister implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8460538149754083576L;
	private Integer id;
	private Users userId;
	private String date;
	private String onTime;
	private String offTime;
	private String normalState;
	private String lateState;
	private String backState;
	private String notRegisterState;
	@GeneratedValue
	@Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@OneToOne
	@JoinColumn(name="userId")
	public Users getUserId() {
		return userId;
	}
	public void setUserId(Users userId) {
		this.userId = userId;
	}
	public String getOnTime() {
		return onTime;
	}
	public void setOnTime(String onTime) {
		this.onTime = onTime;
	}
	public String getOffTime() {
		return offTime;
	}
	public void setOffTime(String offTime) {
		this.offTime = offTime;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getNormalState() {
		return normalState;
	}
	public void setNormalState(String normalState) {
		this.normalState = normalState;
	}
	public String getLateState() {
		return lateState;
	}
	public void setLateState(String lateState) {
		this.lateState = lateState;
	}
	public String getBackState() {
		return backState;
	}
	public void setBackState(String backState) {
		this.backState = backState;
	}
	public String getNotRegisterState() {
		return notRegisterState;
	}
	public void setNotRegisterState(String notRegisterState) {
		this.notRegisterState = notRegisterState;
	}
	
}
