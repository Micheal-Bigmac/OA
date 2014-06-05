package com.oa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="oa_approvehistory")
public class ApproveHistory implements Serializable{

	private Integer id;
	private Date approveTime;
	private Users approver;
	private String comments;
	private Document document;
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getApproveTime() {
		return approveTime;
	}
	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="approver",referencedColumnName="id")
	public Users getApprover() {
		return approver;
	}
	public void setApprover(Users approver) {
		this.approver = approver;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="document",referencedColumnName="id")
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	@Override
	public String toString() {
		return "ApproveHistory [id=" + id + ", approveTime=" + approveTime
				+ ", comments=" + comments + "]";
	}
}
