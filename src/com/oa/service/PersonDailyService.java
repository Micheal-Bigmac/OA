package com.oa.service;

import java.io.Serializable;
import java.util.List;

import com.oa.model.PersonDaily;

public interface PersonDailyService {
	public Serializable addPersonDaily(PersonDaily daily);
	public void deletePersonDailyl(PersonDaily daily);
	public void updatePersonDaily(PersonDaily daily);
	public List<PersonDaily> getAllpersonDailies(String hql);
	public PersonDaily getDaily(Serializable id);
}
