package com.oa.action;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.oa.model.Person;
import com.oa.model.Users;
import com.oa.service.PersonService;
import com.opensymphony.xwork2.ActionSupport;

public class PersonalInfoAction extends ActionSupport {

	private static final long serialVersionUID = -2247441234534761377L;
	private PersonService personService;
	private String returns;
	
	public String selfModify(){
		Users user=(Users) ServletActionContext.getRequest().getSession().getAttribute("admin");
		System.out.println(user.toString());
		System.out.println("=======+++++=====");
		Person person=personService.getPerson(user.getPersonid().getId());
		System.out.println(person.toString());
		ServletActionContext.getRequest().setAttribute("person", person);
		returns="JSP/gerenxinxi.jsp";
		
		return "modify";
	}

	public PersonService getPersonService() {
		return personService;
	}
	
	@Resource
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
}
