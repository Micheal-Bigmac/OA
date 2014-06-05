package com.oa.dao;

import java.io.Serializable;
import java.util.List;
import com.oa.model.Module;
import com.oa.model.Person;
import com.oa.model.Role;
import com.oa.model.UserPrivilege;
import com.oa.model.Users;
import com.oa.model.UsersRoles;

public interface UserDao {
	public Users login(String hql,Object[] conditions);
	
	public Serializable addUser(Users user);
	
	public void deleteUser(Users user) ;
	
	public void updateUser(Users user);
	
	public Users exits(Serializable account);

	public List<Users> select(String sql);

	public List<Person> getPageUsers(int i, Class<Person> clazz, String hql);

	public List<Person> getAllUsers(Class<Person> clazz, String hql);

	public Person getPersonId(Class<Person> clazz, Serializable id);

	public Users getsUser(String sql, Object[] objects);

	public List<Role> listRole(Class<Role> clazz, String sql);

	public List<UsersRoles> getsUsersRoles(Class<UsersRoles> user, String sql);

	public Users getThisUser(Class<Users> clazz, Serializable id);

	public Role getThisRole(String sql, Object[] objects);

	public void addUsersRoles(UsersRoles usersRoles);

	public UsersRoles getThisUsersRoles(Class<UsersRoles> clazz, Serializable id);

	public void updateUsersRoles(UsersRoles usersRoles);

	public UsersRoles getUsersRolesById(Class<UsersRoles> clazz, Serializable id);

	public void deleteUsersRols(UsersRoles usersRoles);

	public Module getThisModule(Class<Module> class1, Serializable moduleId);

	public void addUserPrivilege(UserPrivilege up);

	public UserPrivilege getUserPrivilege(String str, Object[] objects);

	public void deleteUser(UserPrivilege up);

	public void updateUserPrivilege(UserPrivilege up);

	public void updatePerson(Person person);

	public Users getThisUser(Class<Users> clazz, Integer id);

	public Users getUser(String hql,Object []condition);
}
