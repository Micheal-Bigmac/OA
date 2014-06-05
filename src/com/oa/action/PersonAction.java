package com.oa.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.oa.model.Organization;
import com.oa.model.PerformanceExamine;
import com.oa.model.Person;
import com.oa.model.PersonPosition;
import com.oa.model.PersonType;
import com.oa.model.Users;
import com.oa.service.PersonService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.*;
public class PersonAction extends ActionSupport {

	private Person person;
	private Users user;
	private PersonService personService;
	private int index;
	private String returns;
	private Integer type;
	private Integer position;
	private Integer org;
	
	public String edit(){
System.out.println(person.getId());
		Person temPerson=personService.getPerson(person.getId());
		ServletActionContext.getRequest().setAttribute("person", temPerson);
		System.out.println(temPerson.toString());
		return "edit";
	}

	public String addUser() {
		if (person.getId() == null) {
			autoConvertToAge();
			
			HttpSession session = ServletActionContext.getRequest().getSession();
			
			Organization getOrg = ((List<Organization>)session.getAttribute("listOrg")).get(org);
			PersonType getPT = ((List<PersonType>)session.getAttribute("listPT")).get(type);
			PersonPosition getPP = ((List<PersonPosition>)session.getAttribute("listPP")).get(position);
			System.out.println("birthday is "+person.getBirthday());
			person.setOrganization(getOrg);
			person.setPersonPosition(getPP);
			person.setPersonType(getPT);
			
			//获取磁盘中的项目路径，在项目中创建photo文件夹
			String oppositeRoot = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/");
			System.out.println("xiangdui is "+oppositeRoot);
			File folder = new File(oppositeRoot+"photo");
			if(!folder.exists()) {
				boolean createSuccess = folder.mkdirs();
				if(createSuccess) {
					System.out.println("wenjian usccess");
				} else {
					System.out.println("wenjian failure");
				}
			}
			File[] allFiles = folder.listFiles();
			List files = new ArrayList();
			for(int i=0; i<allFiles.length; i++) {
				if(allFiles[i].isFile()) {
					files.add(allFiles[i]);
				}
			}
			String photoName = "";
			for(int i=0; i<files.size(); i++) {
				photoName = ((File)files.get(i)).getName();
			}
			System.out.println("photoname is "+photoName);
			//在数据库中存（http：//）路径
			String path = ServletActionContext.getRequest().getContextPath();
			String basePath = ServletActionContext.getRequest().getScheme()+"://"+ServletActionContext.getRequest().getServerName()+":"+ServletActionContext.getRequest().getServerPort()+path+"/"+"photo"+"/";
			System.out.println("basePath is "+basePath);
			person.setPhotoPath(basePath+photoName);
			Serializable flag = personService.addPerson(person);
			
			returns="JSP/yuangong.jsp";
			return flag == null ? "operator_failure" : "operator_success";
		} else {
			System.out.println("update person");
			returns="PersonAction!personList";
			autoConvertToAge();
			HttpSession session=ServletActionContext.getRequest().getSession();
			System.out.println(" org is null type is null position is null"+org+" "+ " type "+type +" position "+position);
				Organization getOrg=((List<Organization>)session.getAttribute("listOrg")).get(org);
				PersonType getPT=((List<PersonType>)session.getAttribute("listPT")).get(type);
				PersonPosition getPP = ((List<PersonPosition>)session.getAttribute("listPP")).get(position);
				System.out.println("org name is "+getOrg.getId());
				person.setOrganization(getOrg);
				person.setPersonPosition(getPP);
				person.setPersonType(getPT);
			personService.updatePerson(person);
		}
		return "operator_success";
	}

	private void autoConvertToAge() {
		Date now =new Date ();
		Long nowTime=now.getTime();
		Long birthtime=person.getBirthday().getTime();
		Long difference=nowTime-birthtime;
		Long differenceDay=difference/(1000*60*60*34);
		person.setAge((int) (differenceDay/365+1));
	}

	
	public String personList(){
System.out.println("index +  ===="+index);
		personService.getSeletsValue();
		String hql = "";
		List<Person> persons = personService.getPagePersons((index==0 ? 1 : index), Person.class, hql);
		int total = personService.getAllPersons(Person.class, hql).size();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("personList", persons);
		request.setAttribute("currentIndex", (index==0 ?  1 : index ));
		request.setAttribute("totalSize",total);
		
		return SUCCESS;
	}

	public String deletePerson(){
		returns = "PersonAction!personList";
System.out.println("deleteperson");
		if(person != null&& person.getId() != null) {
			person = personService.getPerson(person.getId());
			Users user = personService.getUser("from Users u where u.personid = ?", new Object[]{person});
			List<PerformanceExamine> listPE = personService.getPerFormanceExamine("from PerformanceExamine pe where pe.personId = "+person.getId());
			Integer id[] = new Integer[listPE.size()];
			for(int i = 0; i < listPE.size(); i++) {
				id[i] = listPE.get(i).getId();
			}
			System.out.println("user is ---------"+user);
			if(user != null)
				personService.deleteUser(user);
			if(id.length != 0)
				personService.deletePEs(id);
			personService.deletePerson(person);
		} else {
			HttpServletRequest request = ServletActionContext.getRequest();
			String []ids = request.getParameterValues("delid");
			for (int i = 0; i < ids.length; i++) {
System.out.println(ids[i]);
			}
			if(ids.length != 0){
				personService.deleteUsers(ids);
				personService.deletePEs(ids);
				personService.deletePersons(ids);
			}
		}
		
		return "operator_success";
	}
	public String showListToAdd() {
		
		return "showListToAdd";
	}
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public PersonService getPersonService() {
		return personService;
	}

	@Resource
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getReturns() {
		return returns;
	}

	public void setReturns(String returns) {
		this.returns = returns;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Integer getOrg() {
		return org;
	}

	public void setOrg(Integer org) {
		this.org = org;
	}
	
}
