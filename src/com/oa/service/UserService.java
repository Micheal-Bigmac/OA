package com.oa.service;

import java.io.Serializable;
import java.util.List;
import com.oa.model.*;

public interface UserService {
	public Users login(String hql,Object[] condition);
	public void delete(Users user);
	public void update(Users user);
	public Serializable addUser(Users user);
	public Users exits(Serializable account);
	public List<Person> getPageUsers(int i, Class<Person> clazz, String hql);
	public List<Person> getAllUsers(Class<Person> class1, String hql);
	public Person getPersonId(Class<Person> clazz, Serializable id);
	public Person getsPerson(Class<Person> clazz, Serializable id);
	public Users getsUser(Person person);
	public void deleteAccount(Users user);
	public List<Role> listRole();
	public List<UsersRoles> getUsersRoles(Users user);
	public Users getThisUser(Class<Users> clazz, Serializable id);
	public Role getThisRole(String name);
	public void addUsersRoles(UsersRoles usersRoles);
	public void dealDeleteAccount(Integer id);
	public UsersRoles getThisUsersRoles(int id);
	public void updateUsersRoles(UsersRoles usersRoles, String name);
	public UsersRoles getUsersRolesById(Class<UsersRoles> clazz, Serializable id);
	public void deleteUsersRoles(UsersRoles usersRoles);
	public Module getThisModule(Class<Module> class1, Serializable moduleId);
	public void addUserPrivilege(UserPrivilege up);
	public UserPrivilege getUserPrivilege(String str, Object[] objects);
	//public void delete(UserPrivilege up);
	public void updateUserPrivilege(UserPrivilege up);
	public void update(Person person);
	public Users getThisUser(Class<Users> clazz, Integer id);
	public Users getUsersByAccount(String condition);
}
