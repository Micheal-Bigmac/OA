package com.oa.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.sun.org.apache.bcel.internal.generic.NEW;
/**
 * 
 *  用户登录账号表
 * @author Big mac
 *
 */
@Entity
@Table(name="oa_user")
public class Users implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3334819697053875786L;
	/**
	 * user 表id 自动增长
	 */
	private  Integer id;
	
	/**
	 * user 表 账号 由程序生成 6位 
	 * 
	 */
	private String  account;
	
	/**
	 * user 表 用户 账号 密码
	 * 
	 */
	
	private String password;
	
	/**
	 *  用户创建账号时间
	 *  
	 */
	
	private Date createTime;

	/**
	 *  用户雇佣时间
	 * 
	 */
	private Date expireTime;

	/**
	 * personId
	 * 
	 */
	private Person personid;
	
	private Set<UsersRoles> usersRoles = new HashSet<UsersRoles>();
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(unique=true)
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	@ManyToOne
	@JoinColumn(name="personId",referencedColumnName="id")
	public Person getPersonid() {
		return personid;
	}

	public void setPersonid(Person personid) {
		this.personid = personid;
	}

	@Override
	public String toString() {
		return "User [account=" + account + ", createTime=" + createTime
				+ ", expireTime=" + expireTime + ", id=" + id + ", password="
				+ password + "]";
	}

	@OneToMany(mappedBy="userId",cascade={CascadeType.ALL})
	public Set<UsersRoles> getUsersRoles() {
		return usersRoles;
	}

	public void setUsersRoles(Set<UsersRoles> usersRoles) {
		this.usersRoles = usersRoles;
	}

}
