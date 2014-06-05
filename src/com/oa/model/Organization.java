package com.oa.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * 机构
 * @author cxs
 *
 */
@Entity
@Table(name="oa_organization")
public class Organization implements Serializable{

	public interface OrganizationService {

	}
	private static final long serialVersionUID = 9153552834578848687L;
	/**
	 * 机构编号
	 */
	private Integer id;	
	/**
	 * 机构名称
	 */
	private String name;
	/**
	 * 机构编号
	 */
	private String sn;
	/**
	 * 机构描述
	 */
	private String description;
	
	/**
	 * 父机构的孩子
	 */
	private Set<Organization> oraganOrganizations = new HashSet<Organization>();
	/**
	 * 某机构的父机构
	 */
	private Organization pid;
	
	private Set<Person> persons = new HashSet<Person>();

	
	@Column(unique = true)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(unique = true)
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="pid",referencedColumnName="id")
	public Organization getPid() {
		return pid;
	}
	public void setPid(Organization pid) {
		this.pid = pid;
	}
	
	@OneToMany(cascade={CascadeType.ALL}, mappedBy="organization")
	public Set<Person> getPersons() {
		return persons;
	}
	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}
	
	@OneToMany(mappedBy="pid",cascade={CascadeType.ALL})
	
	public Set<Organization> getOraganOrganizations() {
		return oraganOrganizations;
	}
	public void setOraganOrganizations(Set<Organization> oraganOrganizations) {
		this.oraganOrganizations = oraganOrganizations;
	}
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
	

