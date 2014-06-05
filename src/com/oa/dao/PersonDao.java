package com.oa.dao;

import java.io.Serializable;
import java.util.List;

import com.oa.model.Organization;
import com.oa.model.PerformanceExamine;
import com.oa.model.Person;
import com.oa.model.PersonPosition;
import com.oa.model.PersonType;
import com.oa.model.Users;
import com.oa.dao.*;
public interface PersonDao {

	/* (non-Javadoc)
	 * @see com.oa.dao.impl.PersonDao#addPerson(com.oa.model.Person)
	 */
	public abstract Serializable addPerson(Person person);

	/* (non-Javadoc)
	 * @see com.oa.dao.impl.PersonDao#delete(com.oa.model.Person)
	 */
	public abstract void delete(Person person);

	/* (non-Javadoc)
	 * @see com.oa.dao.impl.PersonDao#update(com.oa.model.Person)
	 */
	public abstract void update(Person person);

	/* (non-Javadoc)
	 * @see com.oa.dao.impl.PersonDao#getPerson(java.io.Serializable)
	 */
	public abstract Person getPerson(Class clazz, Serializable id);

	public abstract List<Person> getAllPersons(Class clazz, String hql);

	public abstract List<Person> getPagePerson(int index,Class clazz, String hql);
	
	public void deletePersons(Class clazz,String ids[],String hql);

	public List<Organization> getAllOrganizations(Class clazz,String hql);
	
	public List<PersonType> getAllPersonTypes(Class clazz,String hql);
	
	public List<PersonPosition> getPersonPositions(Class clazz,String hql);

	public abstract Users getUser(String str, Object[] objects);

	public abstract void delete(Users user);

	public abstract List<PerformanceExamine> getPE(String str);

	public abstract void deletePersons(Class<PerformanceExamine> clazz,
			Integer[] id, String hql);
}