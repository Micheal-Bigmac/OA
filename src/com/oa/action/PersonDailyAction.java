package com.oa.action;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.oa.model.PersonDaily;
import com.oa.model.Rules;
import com.oa.model.Users;
import com.oa.service.PersonDailyService;
import com.oa.service.PersonService;
import com.opensymphony.xwork2.ActionSupport;

public class PersonDailyAction extends ActionSupport{
	private PersonDailyService personDailyService;
	private PersonDaily daily;
	private Integer id;
	public String personDailyList(){
		List<PersonDaily> personDaily=personDailyService.getAllpersonDailies("");
		System.out.println(personDaily.size());
		ServletActionContext.getRequest().setAttribute("personDaily", personDaily);
		ServletActionContext.getRequest().setAttribute("total", personDaily.size());
		return "personDailyList";
	}
	public String addUserManual(){
		Users users=(Users) ServletActionContext.getRequest().getSession().getAttribute("admin");
		
		if(daily.getId()==null){
			daily.setUsers(users);
			Serializable flag=personDailyService.addPersonDaily(daily);
			return flag == null ? "operator_failure" : "operator_success";
		}
		
		personDailyService.updatePersonDaily(daily);
		return "operator_success";
	}
	public String addUserManualView(){
		daily=personDailyService.getDaily(id);
		ServletActionContext.getRequest().setAttribute("daily", daily);
		return "addUserManualView";
	}

	public PersonDaily getDaily() {
		return daily;
	}

	public void setDaily(PersonDaily daily) {
		this.daily = daily;
	}

	public PersonDailyService getPersonDailyService() {
		return personDailyService;
	}

	@Resource
	public void setPersonDailyService(PersonDailyService personDailyService) {
		this.personDailyService = personDailyService;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
