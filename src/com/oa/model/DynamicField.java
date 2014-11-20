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
import javax.persistence.OrderBy;
import javax.persistence.Table;
@Entity
@Table(name="oa_dynamicField")
public class DynamicField implements Serializable{
	private Integer id;
	private String fieldLabel;
	private String fieldName;
	
	//
	private FiledInput input;
	private FieldType type;
	
	//
	private DynamicForm dynamicForm;
	
	///
	private Set<FieldItem> fieldItems=new HashSet<FieldItem>();
	@Id 
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFieldLabel() {
		return fieldLabel;
	}
	public void setFieldLabel(String fieldLabel) {
		this.fieldLabel = fieldLabel;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fieldInput", referencedColumnName="id")
	public FiledInput getInput() {
		return input;
	}
	public void setInput(FiledInput input) {
		this.input = input;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fieldtype",referencedColumnName="id")
	public FieldType getType() {
		return type;
	}
	public void setType(FieldType type) {
		this.type = type;
	}
	@OneToMany(mappedBy="dynamicField",cascade={CascadeType.ALL})
	@OrderBy("id ASC")
	public Set<FieldItem> getFieldItems() {
		return fieldItems;
	}
	public void setFieldItems(Set<FieldItem> fieldItems) {
		this.fieldItems = fieldItems;
	}
	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.ALL})
	@JoinColumn(name="dynamicFormId",referencedColumnName="id")
	public DynamicForm getDynamicForm() {
		return dynamicForm;
	}
	public void setDynamicForm(DynamicForm dynamicForm) {
		this.dynamicForm = dynamicForm;
	}
	@Override
	public String toString() {
		return "DynamicField [id=" + id + ", fieldLabel=" + fieldLabel
				+ ", fieldName=" + fieldName + ", input=" + input + ", type="
				+ type + "]";
	}
	
	
}
