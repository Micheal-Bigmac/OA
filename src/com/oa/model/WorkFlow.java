package com.oa.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 流程
 * @author Big mac
 *
 */
@Entity
@Table(name="oa_workflow")
public class WorkFlow implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5263162594166687918L;
	private Integer id;
	private String name;
	private String processDefinition;
	private String processImage;
	
	//
	private Set<Document> documents=new HashSet<Document>();
	
	///
	private DynamicForm form;
	
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

	@OneToMany(mappedBy="workFlow",cascade={CascadeType.ALL})
	public Set<Document> getDocuments() {
		return documents;
	}
	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}
	public String getProcessImage() {
		return processImage;
	}
	public void setProcessImage(String processImage) {
		this.processImage = processImage;
	}
	public String getProcessDefinition() {
		return processDefinition;
	}
	public void setProcessDefinition(String processDefinition) {
		this.processDefinition = processDefinition;
	}
	@Override
	public String toString() {
		return "WorkFlow [id=" + id + ", name=" + name + ", processDefinition="
				+ processDefinition + ", processImage=" + processImage + "]";
	}
	@OneToOne(mappedBy="workFlow")
	public DynamicForm getForm() {
		return form;
	}
	public void setForm(DynamicForm form) {
		this.form = form;
	}
	
}
