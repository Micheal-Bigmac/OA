package com.oa.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "oa_dynamicForm")
public class DynamicForm implements Serializable{
	private Integer id;
	private WorkFlow workFlow;
	private String template;

	//

	private Set<DynamicField> dynamicFields = new HashSet<DynamicField>();

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	// @ManyToOne(fetch=FetchType.LAZY)
	// @JoinColumn(name="workflowId",referencedColumnName="id")

	@OneToOne
	@JoinColumn(name = "workflowId")
	public WorkFlow getWorkFlow() {
		return workFlow;
	}

	public void setWorkFlow(WorkFlow workFlow) {
		this.workFlow = workFlow;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	@OneToMany(mappedBy = "dynamicForm", cascade = { CascadeType.ALL })
	@OrderBy("id ASC")
	public Set<DynamicField> getDynamicFields() {
		return dynamicFields;
	}

	public void setDynamicFields(Set<DynamicField> dynamicFields) {
		this.dynamicFields = dynamicFields;
	}

	@Override
	public String toString() {
		return "DynamicForm [id=" + id + ", template=" + template + "]";
	}

	@Transient
	public DynamicField getDynamicField() {
		if (dynamicFields != null) {
			return dynamicFields.iterator().next();
		} else {
			return null;
		}

	}
}
