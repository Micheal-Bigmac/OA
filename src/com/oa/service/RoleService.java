package com.oa.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.oa.model.Acl;
import com.oa.model.Module;
import com.oa.model.Role;
import com.oa.model.UserPrivilege;
import com.oa.model.Users;
import java.util.LinkedHashMap;

public interface RoleService {

	public List<Role> find();

	public void deleteRole(Integer id);

	public void update(Role role);

	public void add(Role role);

	public List<Role> getAllRole(Class<Role> clazz, String hql);

	public Role getUpdateRole(Serializable id);

	public Acl getsAcl(Role role);

	public Module getsModule(Class<Module> clazz, Serializable id);

	public List<Module> getModules();

	public List<Module> getChildModules(Integer id);

	public LinkedHashMap<Module, LinkedHashMap<Module, List>> getCategories(Role role);

	public void addAcl(Acl acl);

	public boolean ExistsAcl(Role role, Module module);

	public Acl getOrgBoxValue(Role role, Module module);

	public void updateAcl(Acl acl);

	public Users getsUsers(Class<Users> clazz, Serializable userId);

	public void addUserPrivilege(UserPrivilege up);

	
	
}
