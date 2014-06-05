package com.oa.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;

import com.oa.model.Organization;
import com.oa.service.OrganizationService;
import com.oa.dao.OrganizationDao;

@Component("organizationService")
public class OrganizationServiceImpl implements OrganizationService {

	private OrganizationDao organizationDao;

	
	public Serializable add(Object model) {
		return organizationDao.add(model);
	}

	public OrganizationDao getOrganizationDao() {
		return organizationDao;
	}

	@Resource
	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	
	public void update(Object model) {
		organizationDao.update(model);
	}
	
	public void delete(Object model) {
		organizationDao.delete(model);
	}
	
	public Organization select(Class<Organization> clazz, Integer id) {
		return organizationDao.select(clazz,id);

	}

	public Organization check(String hql, Object[] objects) {
		return organizationDao.check(hql,objects);
	}

	public List<Organization> getPageOrganizations(int index, Class<Organization> clazz, String hql) {
		return organizationDao.getPageOrganizations(index, clazz, hql);
	}

	public List<Organization> getAllOrganizations(Class<Organization> clazz,String hql) {
		return organizationDao.getAllOrganizations(clazz, hql);
	}

	public Organization getModel(Integer parentid) {
		return organizationDao.getModel(parentid);
	}

}
