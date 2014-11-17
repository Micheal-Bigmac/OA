package com.oa.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * 公文
 * @author Big mac
 *
 */

@Entity
@Table(name="oa_document")
public class Document implements Serializable{
	public static String New="新建";
	public static String Finish="完成";
	/**
	 * 文档 id
	 */
	private Integer id;
	/**
	 * 文档标题
	 */
	private String title;
	/**
	 * 文档内容
	 */
	private String doc;
	/**
	 * 文档描述
	 */
	private String description;
	/**
	 * 文档创建时间
	 */
	private Date createTime;
	/**
	 * 文档状态
	 */
	private String status;
	/**
	 * 创建者
	 */
	private Users users;
	/**
	 * 所属工作流程
	 */
	private WorkFlow workFlow;
	
	private Long processInstanceId;
	
	//持久化在本地文件的Key
	private String typePersist;
	
	//持久化本地key对应的URL
	private String url;
	//
	private Set<DocumentProperty> props=new HashSet<DocumentProperty>();
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Temporal(TemporalType.DATE)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="creator", referencedColumnName="id")
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="workflow",referencedColumnName="id")
	public WorkFlow getWorkFlow() {
		return workFlow;
	}
	public void setWorkFlow(WorkFlow workFlow) {
		this.workFlow = workFlow;
	}

	public Long getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(Long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}
	@Override
	public String toString() {
		return "Document [id=" + id + ", title=" + title + ", doc=" + doc
				+ ", description=" + description + ", createTime=" + createTime
				+ ", status=" + status + "]";
	}
	@OneToMany(mappedBy="document",cascade={CascadeType.ALL})
	public Set<DocumentProperty> getProps() {
		return props;
	}
	public void setProps(Set<DocumentProperty> props) {
		this.props = props;
	}
	public String getTypePersist() {
		return typePersist;
	}
	public void setTypePersist(String typePersist) {
		this.typePersist = typePersist;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
