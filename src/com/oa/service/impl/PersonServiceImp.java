package com.oa.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;
import com.oa.dao.PersonDao;
import com.oa.model.*;
import com.oa.service.PersonService;

@Component("personService")
public class PersonServiceImp implements PersonService {
	private PersonDao personDao;
	static final List<Organization> listOrg = new ArrayList<Organization>();
	static final List<PersonType> listPT = new ArrayList<PersonType>();
	static final List<PersonPosition> listPP = new ArrayList<PersonPosition>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oa.service.impl.PersonService#addPerson(com.oa.model.Person)
	 */
	public Serializable addPerson(Person person) {
		return personDao.addPerson(person);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oa.service.impl.PersonService#updatePerson(com.oa.model.Person)
	 */
	public void updatePerson(Person person) {
		personDao.update(person);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oa.service.impl.PersonService#deletePerson(com.oa.model.Person)
	 */
	public void deletePerson(Person person) {
		personDao.delete(person);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oa.service.impl.PersonService#getPerson(java.io.Serializable)
	 */
	public Person getPerson(Serializable id) {
		return personDao.getPerson(Person.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oa.service.impl.PersonService#getAllPersons(java.lang.Class,
	 * java.lang.String)
	 */
	public List<Person> getAllPersons(Class clazz, String hql) {
		return personDao.getAllPersons(Person.class, hql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oa.service.impl.PersonService#getPagePersons(int,
	 * java.lang.Class, java.lang.String)
	 */
	public List<Person> getPagePersons(int index, Class clazz, String hql) {
		return personDao.getPagePerson(index, clazz, hql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oa.service.impl.PersonService#getPersonDao()
	 */
	public PersonDao getPersonDao() {
		return personDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oa.service.impl.PersonService#setPersonDao(com.oa.dao.PersonDao)
	 */
	@Resource
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	public void deletePersons(String ids[]) {
		String hql = "delete from Person p where p.id ";
		personDao.deletePersons(Person.class, ids, hql);
	}

	public void getSeletsValue() {
		objectToListOrg(" and not exists(select id from Organization o2 where s.id=o2.pid.id)");
		objectToListPT(null);
		objectToListPP(null);

		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("listOrg", listOrg);
		session.setAttribute("listPT", listPT);
		session.setAttribute("listPP", listPP);
	}

	private void objectToListOrg(String hql) {
		System.out.println();
		if (listOrg.size() == 0) {
			List<Organization> list =personDao.getAllOrganizations(Organization.class, hql);
			System.err.println(" list org is null");
			for (Organization o : list) {
				listOrg.add(o);
			}
		} 
	}

	private void objectToListPT(String hql) {
		if (listPT.size() == 0) {
			List<PersonType> list=personDao.getAllPersonTypes(PersonType.class, hql);
			System.err.println(" list org is null");
			for (PersonType o : list) {
				listPT.add(o);
			}
		} 
	}

	private void objectToListPP(String hql) {
		if (listPP.size() == 0) {
			List<PersonPosition> list=personDao.getPersonPositions(PersonPosition.class, hql);
			System.err.println(" list org is null");
			for (PersonPosition o : list) {
				listPP.add(o);
			}
		} 
	}

	public Users getUser(String str, Object[] objects) {
		return personDao.getUser(str, objects);
	}

	public void deleteUser(Users user) {
		personDao.delete(user);
	}

	public void deleteUsers(String[] ids) {
		String hql = "delete from Users u where u.personid ";
		personDao.deletePersons(Users.class, ids, hql);
	}

	public List<PerformanceExamine> getPerFormanceExamine(String str) {
		return personDao.getPE(str);
	}

	public void deletePEs(Integer[] id) {
		String hql = "delete from PerformanceExamine pe where pe.id ";
		personDao.deletePersons(PerformanceExamine.class, id, hql);
	}

	public void deletePEs(String[] id) {
		String hql = "delete from PerformanceExamine pe where pe.id ";
		personDao.deletePersons(PerformanceExamine.class, id, hql);
	}
}
