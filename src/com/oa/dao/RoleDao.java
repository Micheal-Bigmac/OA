package com.oa.dao;

import java.io.Serializable;
import java.util.List;

import com.oa.model.Acl;
import com.oa.model.Module;
import com.oa.model.Role;
import com.oa.model.UserPrivilege;
import com.oa.model.Users;
import com.oa.model.UsersRoles;

public interface RoleDao {

	List<Role> find(String sql);

	Role getRoleById(Serializable id);

	void deleteRole(Role role);

	void update(Role role);

	void add(Role role);

	List<Role> getAllRole(Class<Role> clazz, String hql);

	Acl getsAcl(String sql, Object[] objects);

	Module getsModule(Class<Module> clazz, Serializable id);

	List<Module> getsModules(String sql);

	List<Object> getAllObjects(Class clazz, String hql);

	Acl getAclById(Integer id);

	List<UsersRoles> getUsersroles(String sql);

	Acl getAclById(String sql2, Object[] objects);

	void addAcl(Acl acl);

	boolean ExistsAcl(String sql, Object[] objects);

	Acl getAcl(String sql, Object[] objects);

	void updateAcl(Acl acl);

	Module getRoleToModules(String str, Object[] objects);

	Users getsUsers(Class<Users> clazz, Serializable userId);

	void addUserPrivilege(UserPrivilege up);
}
