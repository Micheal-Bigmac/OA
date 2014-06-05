package com.oa.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;

import com.sun.jmx.snmp.Timestamp;

@Entity
@Table(name="oa_person")
public class Person implements Serializable{

	private static final long serialVersionUID = -9169062886981751173L;
	private Integer id;
	private String name;
	private String sex;
	private Integer age;
	private String address;
	private String email;
	private String phone;
	private Date birthday;
	private Date  hiredate;
	private String photoPath;
	private Organization organization;
	private PersonType personType;
	private PersonPosition personPosition;
	private Set<Users> users=new HashSet<Users>();
	private Users firstUser;
	
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@ManyToOne
	@JoinColumn(name="orgId", referencedColumnName = "id")
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	@OneToMany(mappedBy="personid",cascade={CascadeType.ALL})
	public Set<Users> getUsers() {
		return users;
	}
	public void setUsers(Set<Users> users) {
		this.users = users;
	}
	@Temporal(TemporalType.DATE)
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Temporal(TemporalType.DATE)
	public Date getHiredate() {
		return hiredate;
	}
	
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	
	@Override
	public String toString() {
		return "Person [address=" + address + ", age=" + age + ", birthday="
				+ birthday + ", email=" + email + ", hiredate=" + hiredate
				+ ", id=" + id + ", name=" + name + ", phone=" + phone
				+ ", position=" + personPosition + ", sex=" + sex + ", type=" + personType
				+ "]";
	}
	@OneToOne
	@JoinColumn(name="personTypeId")
	public PersonType getPersonType() {
		return personType;
	}
	public void setPersonType(PersonType personType) {
		this.personType = personType;
	}
	@OneToOne
	@JoinColumn(name="personPositionId")
	public PersonPosition getPersonPosition() {
		return personPosition;
	}
	public void setPersonPosition(PersonPosition personPosition) {
		this.personPosition = personPosition;
	}
	@Transient
	public Users getFirstUser() {
		return users.iterator().next();
	}
	public void setFirstUser(Users firstUser) {
		this.firstUser = firstUser;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

}
