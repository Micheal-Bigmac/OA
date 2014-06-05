package com.oa.model;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="oa_userPrivilege")
public class UserPrivilege {

	private Integer id;
	private Users userId;
	private Module moduleId;
	private Integer userValue;
	private Integer inheritance;	//(0--继承 -1---不继承)
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userId", referencedColumnName = "id")
	public Users getUserId() {
		return userId;
	}
	public void setUserId(Users userId) {
		this.userId = userId;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="moduleId", referencedColumnName = "id")
	public Module getModuleId() {
		return moduleId;
	}
	public void setModuleId(Module moduleId) {
		this.moduleId = moduleId;
	}
	public Integer getUserValue() {
		return userValue;
	}
	public void setUserValue(Integer userValue) {
		this.userValue = userValue;
	}
	public Integer getInheritance() {
		return inheritance;
	}
	public void setInheritance(Integer inheritance) {
		this.inheritance = inheritance;
	}
	
}
