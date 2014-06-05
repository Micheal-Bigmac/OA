package com.oa.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;



/***
 * 
 * 模块表
 * @author Big mac
 *
 */
@Entity
@Table(name="oa_module")
public class Module implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8626305393798148323L;
	
	/**
	 * 模块id 自定增长
	 * 
	 */
	private Integer id;
	
	/**
	 * 模块名称
	 * 
	 */
	private String name;

	/***
	 * 模块的url (action)
	 */
	private String url;
	
	/**
	 * 模块编号
	 * 
	 */
	private String sn;
	
	
	/**
	 * 
	 * module 父id
	 */
	private Module pid;
	
	private Set<Module> modules =new HashSet<Module>();

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(unique=true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSn() {
		return sn;
	}
	
	public void setSn(String sn) {
		this.sn = sn;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="pid",referencedColumnName="id")
	@OrderBy("pid asc")
	public Module getPid() {
		return pid;
	}

	public void setPid(Module pid) {
		this.pid = pid;
	}

	@OneToMany(mappedBy="pid",cascade={CascadeType.ALL})
	@OrderBy("id asc")
	public Set<Module> getModules() {
		return modules;
	}

	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}

	@Override
	public String toString() {
		return "Module [id=" + id + ", name=" + name + ", sn=" + sn + ", url="
				+ url + "]";
	}

}
