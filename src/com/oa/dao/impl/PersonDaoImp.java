package com.oa.dao.impl;

import java.io.Serializable;
import java.util.*;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.oa.dao.PersonDao;
import com.oa.dao.SuperDaoInte;
import com.oa.model.*;
@Component("personDao")
public class PersonDaoImp implements PersonDao {

	private SuperDaoInte superDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oa.dao.impl.PersonDao#addPerson(com.oa.model.Person)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oa.dao.impl.PersonDao#addPerson(com.oa.model.Person)
	 */
	public Serializable addPerson(Person person) {
		return superDao.add(person);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oa.dao.impl.PersonDao#delete(com.oa.model.Person)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oa.dao.impl.PersonDao#delete(com.oa.model.Person)
	 */
	public void delete(Person person) {
		superDao.delete(person);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oa.dao.impl.PersonDao#update(com.oa.model.Person)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oa.dao.impl.PersonDao#update(com.oa.model.Person)
	 */
	public void update(Person person) {
		superDao.update(person);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oa.dao.impl.PersonDao#getPerson(java.io.Serializable)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oa.dao.impl.PersonDao#getPerson(java.lang.Class,
	 * java.io.Serializable)
	 */
	public Person getPerson(Class clazz, Serializable id) {
		return (Person) superDao.select(clazz, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oa.dao.impl.PersonDao#getAllPersons(java.lang.Class,
	 * java.lang.String)
	 */
	public List<Person> getAllPersons(Class clazz, String hql) {
		return objectToList(superDao.getAllObjects(Person.class, hql));
	}

	private List<Person> objectToList(List<Object> list) {
		List<Person> persons = new ArrayList<Person>();
		for (Object o : list) {
			persons.add((Person) o);
		}
		return persons;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oa.dao.impl.PersonDao#getPagePerson(java.lang.Class,
	 * java.lang.String)
	 */
	public List<Person> getPagePerson(int index, Class clazz, String hql) {
		return objectToList(superDao.getPage(index, clazz, hql));
	}

	public SuperDaoInte getSuperDao() {
		return superDao;
	}

	@Resource
	public void setSuperDao(SuperDaoInte superDao) {
		this.superDao = superDao;
	}

	public void deletePersons(Class clazz, String[] ids, String hql) {
		superDao.deleteList(clazz, ids, hql);
	}

	
	public List<Organization> getAllOrganizations(Class clazz,String hql){
		return objectToListOrg(superDao.getAllObjects(clazz, hql));
	}
	public List<PersonType> getAllPersonTypes(Class clazz,String hql){
		return objectToListPT(superDao.getAllObjects(clazz, hql));
	}
	public List<PersonPosition> getPersonPositions(Class clazz,String hql){
		return objectToListPP(superDao.getAllObjects(clazz, hql));
	}

	private List<Organization> objectToListOrg(List<Object> list) {
		List<Organization> listOrg = new ArrayList<Organization>();

		for (Object o : list) {
			listOrg.add((Organization) o);
		}
		return listOrg;
	}

	private List<PersonType> objectToListPT(List<Object> list) {
		List<PersonType> listPT = new ArrayList<PersonType>();
		for (Object o : list) {
			listPT.add((PersonType) o);
		}
		return listPT;
	}

	private List<PersonPosition> objectToListPP(List<Object> list) {
		List<PersonPosition> listPP = new ArrayList<PersonPosition>();
		for (Object o : list) {
			listPP.add((PersonPosition) o);
		}
		return listPP;
	}

	public Organization selectOrg(Class<Organization> clazz, String org) {
		return (Organization) superDao.select(clazz, org);
	}

	public PersonPosition selectPP(Class<PersonPosition> clazz, String pp) {
		return (PersonPosition) superDao.select(clazz, pp);
	}

	public PersonType selectPT(Class<PersonType> clazz, String pt) {
		return (PersonType) superDao.select(clazz, pt);
	}

	public Object select(String sql, Object[] condition) {
		return superDao.check(sql, condition);
	}

	public Users getUser(String sql, Object[] condition) {
		return (Users)superDao.check(sql, condition);
	}

	public void delete(Users user) {
		superDao.delete(user);
	}

	public List<PerformanceExamine> getPE(String str) {
		return convertToPE(superDao.find(str));
	}
	
	public List<PerformanceExamine> convertToPE(List<Object> objects) {	
		List<PerformanceExamine> list = new ArrayList<PerformanceExamine>();
		for(int i=0; i<objects.size(); i++) {
			list.add((PerformanceExamine)objects.get(i));
		}
		return list;
	}

	public void deletePersons(Class<PerformanceExamine> clazz, Integer[] id,
			String hql) {
		superDao.deleteList(clazz, id, hql);
	}
}
