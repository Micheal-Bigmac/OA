package com.oa.service;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import com.oa.dao.PersonDao;
import com.oa.model.*;

public interface PersonService {

	public abstract Serializable addPerson(Person person);

	public abstract void updatePerson(Person person);

	public abstract void deletePerson(Person person);

	public abstract Person getPerson(Serializable id);

	public abstract List<Person> getAllPersons(Class clazz, String hql);

	public abstract List<Person> getPagePersons(int index, Class clazz,
			String hql);

	public abstract PersonDao getPersonDao();

	@Resource
	public abstract void setPersonDao(PersonDao personDao);
	
	public void deletePersons(String ids[]);

	public void getSeletsValue();

	public abstract Users getUser(String str, Object[] objects);

	public abstract void deleteUser(Users user);

	public abstract void deleteUsers(String[] ids);

	public abstract List<PerformanceExamine> getPerFormanceExamine(String string);

	public abstract void deletePEs(Integer[] id);

	public abstract void deletePEs(String[] ids);
}