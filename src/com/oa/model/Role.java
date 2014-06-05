package com.oa.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import com.sun.org.apache.bcel.internal.generic.NEW;

@Entity
@Table(name="oa_role")
public class Role implements Serializable{
	
	private static final long serialVersionUID = -1217742198816383650L;
	private Integer id;
	private String name;
	private Set<UsersRoles> usersRoles = new HashSet<UsersRoles>();
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
	@OneToMany(cascade={CascadeType.ALL},mappedBy="roleId")
	public Set<UsersRoles> getUsersRoles() {
		return usersRoles;
	}
	public void setUsersRoles(Set<UsersRoles> usersRoles) {
		this.usersRoles = usersRoles;
	}
}
