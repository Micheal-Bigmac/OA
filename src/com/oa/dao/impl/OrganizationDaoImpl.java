package com.oa.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.aspectj.weaver.patterns.OrAnnotationTypePattern;
import org.springframework.stereotype.Component;

import com.oa.dao.OrganizationDao;
import com.oa.dao.SuperDaoInte;
import com.oa.model.Organization;

@Component("organizationDao")
public class OrganizationDaoImpl implements OrganizationDao {

	private SuperDaoInte superDao;

	public Serializable add(Object model) {
		return superDao.add(model);
	}

	public void update(Object model) {
		superDao.update(model);
	}
	
	public void delete(Object model) {
		superDao.delete(model);
	}
	
	public List<Organization> getPageOrganizations(int index, Class<Organization> clazz, String hql) {
		return ConvertObject(superDao.getPage(index, clazz, hql));
	}
	
	public List<Organization> ConvertObject(List<Object> list) {
		List<Organization> listOrg =new ArrayList<Organization>();
		//System.out.println("covert size is "+list.size());
		
		for(int i=0; i<list.size(); i++) {
			listOrg.add((Organization)list.get(i));
		}
		return listOrg;
	}
	
	public SuperDaoInte getSuperDao() {
		return superDao;
	}
	@Resource
	public void setSuperDao(SuperDaoInte superDao) {
		this.superDao = superDao;
	}

	public Organization select(Class<Organization> clazz, Integer id) {
		return (Organization)superDao.select(clazz, id);
	}

	public Organization check(String hql, Object[] objects) {
		return (Organization) superDao.check(hql, objects);
	}

	public List<Organization> getAllOrganizations(Class<Organization> clazz,
			String hql) {
		return ConvertObject(superDao.getAllObjects(clazz, hql));
	}

	public Organization getModel(Integer parentid) {
		return (Organization)superDao.select(Organization.class, parentid);
	}
	
	
}
