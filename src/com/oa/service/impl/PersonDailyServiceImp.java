package com.oa.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.oa.dao.impl.SuperDao;
import com.oa.model.PersonDaily;
import com.oa.service.PersonDailyService;
@Component("personDailyService")
public class PersonDailyServiceImp implements PersonDailyService {

	private SuperDao superDao;
	public Serializable addPersonDaily(PersonDaily daily) {
		return superDao.add(daily);
	}

	public void deletePersonDailyl(PersonDaily daily) {
		superDao.delete(daily);
	}

	public List<PersonDaily> getAllpersonDailies(String hql) {
		return objectToRules(superDao.getAllObjects(PersonDaily.class, hql));
	}
	private List<PersonDaily> objectToRules(List<Object> list){
		List<PersonDaily> dailys=new ArrayList<PersonDaily>();
		for(Object o: list){
			dailys.add((PersonDaily)o);
		}
		return dailys;
	}

	public PersonDaily getDaily(Serializable id) {
		return (PersonDaily) superDao.select(PersonDaily.class, id);
	}

	public SuperDao getSuperDao() {
		return superDao;
	}

	@Resource
	public void setSuperDao(SuperDao superDao) {
		this.superDao = superDao;
	}

	public void updatePersonDaily(PersonDaily daily) {
			superDao.update(daily);
	}

}
