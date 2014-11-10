package com.oa.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "oa_documentProperty")
public class DocumentProperty {

	private Integer id;

	private String java_lang_String;
	private String java_util_Date;
	private String java_lang_Integer;
	private String java_lang_Long;
	private String java_io_File;
	private String propertyName;

	//
	private Document document;

	String getJava_lang_String() {
		return java_lang_String;
	}

	public void setJava_lang_String(String java_lang_String) {
		this.java_lang_String = java_lang_String;
	}

	public String getJava_util_Date() {
		return java_util_Date;
	}

	public void setJava_util_Date(String java_util_Date) {
		this.java_util_Date = java_util_Date;
	}

	public String getJava_lang_Integer() {
		return java_lang_Integer;
	}

	@Transient
	public boolean isNull() {
		if (this.java_lang_String != null || this.java_io_File != null
				|| this.java_lang_Integer != null
				|| this.java_lang_Long != null
				|| this.java_util_Date!=null) {
			return false;
		}
		return true;
	}
	@Transient
	public Object getValue(){
		if(this.java_io_File!=null) return java_io_File;
		else if(java_lang_Integer!=null) return java_lang_Integer;
		else if(java_lang_Long!=null) return java_lang_Long;
		else if(java_lang_String!=null) return java_lang_String;
		else return java_util_Date;
	}

	public void setJava_lang_Integer(String java_lang_Integer) {
		this.java_lang_Integer = java_lang_Integer;
	}

	public String getJava_lang_Long() {
		return java_lang_Long;
	}

	public void setJava_lang_Long(String java_lang_Long) {
		this.java_lang_Long = java_lang_Long;
	}

	public String getJava_io_File() {
		return java_io_File;
	}

	public void setJava_io_File(String java_io_File) {
		this.java_io_File = java_io_File;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "documentId", referencedColumnName = "id")
	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	@Override
	public String toString() {
		return "DocumentProperty [id=" + id + ", java_lang_String="
				+ java_lang_String + ", java_util_Date=" + java_util_Date
				+ ", java_lang_Integer=" + java_lang_Integer
				+ ", java_lang_Long=" + java_lang_Long + ", java_io_File="
				+ java_io_File + ", propertyName=" + propertyName + "]";
	}
}
