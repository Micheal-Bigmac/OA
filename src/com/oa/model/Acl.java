package com.oa.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="oa_acl")
public class Acl implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7012078162997556118L;
	private Integer id;
	private String principalType;
	private Role principalId;
	private Module moduleId;
	private Integer aclState;
	private Integer alcTriState;
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPrincipalType() {
		return principalType;
	}
	public void setPrincipalType(String principalType) {
		this.principalType = principalType;
	}
	@ManyToOne
	@JoinColumn(name="moduleId", referencedColumnName = "id")
	public Module getModuleId() {
		return moduleId;
	}
	public void setModuleId(Module moduleId) {
		this.moduleId = moduleId;
	}
	public Integer getAclState() {
		return aclState;
	}
	public void setAclState(Integer aclState) {
		this.aclState = aclState;
	}
	public Integer getAlcTriState() {
		return alcTriState;
	}
	public void setAlcTriState(Integer alcTriState) {
		this.alcTriState = alcTriState;
	}
	@ManyToOne
	@JoinColumn(name="principalId", referencedColumnName = "id")
	public Role getPrincipalId() {
		return principalId;
	}
	public void setPrincipalId(Role principalId) {
		this.principalId = principalId;
	}
}
