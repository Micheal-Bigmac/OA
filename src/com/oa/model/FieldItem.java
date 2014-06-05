package com.oa.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="oa_fieldItem")
public class FieldItem implements Serializable{
	private Integer id;
	private String label;
	private String itemValues;
	private Integer indexItem;
	
	//
	private DynamicField dynamicField;
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fieldId",referencedColumnName="id")
	public DynamicField getDynamicField() {
		return dynamicField;
	}
	public void setDynamicField(DynamicField dynamicField) {
		this.dynamicField = dynamicField;
	}
	public Integer getIndexItem() {
		return indexItem;
	}
	public void setIndexItem(Integer indexItem) {
		this.indexItem = indexItem;
	}
	public String getItemValues() {
		return itemValues;
	}
	public void setItemValues(String itemValues) {
		this.itemValues = itemValues;
	}
	@Override
	public String toString() {
		return "FieldItem [id=" + id + ", label=" + label + ", itemValues="
				+ itemValues + ", indexItem=" + indexItem + "]";
	}
}
