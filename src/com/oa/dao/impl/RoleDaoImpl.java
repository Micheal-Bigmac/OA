package com.oa.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.oa.dao.RoleDao;
import com.oa.dao.SuperDaoInte;
import com.oa.model.Acl;
import com.oa.model.Module;
import com.oa.model.Role;
import com.oa.model.UserPrivilege;
import com.oa.model.Users;
import com.oa.model.UsersRoles;
@Component("roleDao")
public class RoleDaoImpl implements RoleDao{
	private SuperDaoInte superDao;

	public SuperDaoInte getSuperDao() {
		return superDao;
	}
	
	@Resource
	public void setSuperDao(SuperDaoInte superDao) {
		this.superDao = superDao;
	}

	public List<Role> find(String sql) {
		return converToRole(superDao.find(sql));
	}
	
	public List<Role> converToRole(List<Object> list) {
		List<Role> roles = new ArrayList<Role>();
		for(int i=0; i<list.size(); i++) {
			roles.add((Role)list.get(i));
		}
		return roles;
	}

	public Role getRoleById(Serializable id) {
		//System.out.println("daoimpl is ok");
		return (Role)superDao.select(Role.class, id);
	}

	public void deleteRole(Role role) {
		superDao.delete(role);
	}

	public void update(Role role) {
		superDao.update(role);
	}

	public void add(Role role) {
		superDao.add(role);
	}

	public List<Role> getAllRole(Class<Role> clazz, String hql) {
		return converToRole(superDao.getAllObjects(clazz, hql));
	}

	public Acl getsAcl(String sql, Object[] objects) {
		return (Acl)superDao.check(sql, objects);
	}

	public Module getsModule(Class<Module> clazz, Serializable id) {
		return (Module)superDao.select(clazz, id);
	}

	public List<Module> getsModules(String sql) {
		return convertToModule(superDao.find(sql));
	}
	
	public List<Module> convertToModule(List<Object> objects) {
		List<Module> modules = new ArrayList<Module>();
		for(int i=0; i<objects.size(); i++) {
			modules.add((Module)objects.get(i));
		}
		return modules;
	}

	public List<UsersRoles> convertToUsersRoles(List<Object> objects) {
		List<UsersRoles> usersRoles = new ArrayList<UsersRoles>();
		for(int i=0; i<objects.size(); i++) {
			usersRoles.add((UsersRoles)objects.get(i));
		}
		return usersRoles;
	}
	
	public List<Object> getAllObjects(Class clazz, String hql) {
		return superDao.getAllObjects(clazz, hql);
	}

	public Acl getAclById(Integer id) {
		return (Acl)superDao.select(Acl.class, id);
	}

	public List<UsersRoles> getUsersroles(String sql) {
		return convertToUsersRoles(superDao.find(sql));
	}


	public Acl getAclById(String sql2, Object[] objects) {
		return (Acl)superDao.check(sql2, objects);
	}

	public void addAcl(Acl acl) {
		superDao.add(acl);
	}

	public boolean ExistsAcl(String sql, Object[] objects) {
		boolean flag = false;
		Acl acl = (Acl)superDao.check(sql, objects);
		if(acl == null) {
			flag = false;
		} else {
			flag = true;
		}
		return flag; 
	}

	public Acl getAcl(String sql, Object[] objects) {
		return (Acl)superDao.check(sql, objects);
	}

	public void updateAcl(Acl acl) {
		superDao.update(acl);
	}

	public Module getRoleToModules(String sql, Object[] objects) {
		return (Module)superDao.check(sql, objects);
	}

	public Users getsUsers(Class<Users> clazz, Serializable userId) {
		return (Users)superDao.select(clazz, userId);
	}

	public void addUserPrivilege(UserPrivilege up) {
		superDao.add(up);
	}

}