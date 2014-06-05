package com.oa.dao;

import java.io.Serializable;
import java.util.List;

import com.oa.model.Organization;

public interface OrganizationDao {
	public Serializable add(Object model);
	public void update(Object model);
	public void delete(Object model);
	public Organization select(Class<Organization> clazz, Integer id);
	public Organization check(String hql, Object[] objects);
	public List<Organization> getPageOrganizations(int index, Class<Organization> clazz, String hql);
	public List<Organization> getAllOrganizations(Class<Organization> clazz, String hql);
	public Organization getModel(Integer parentid);		
}
