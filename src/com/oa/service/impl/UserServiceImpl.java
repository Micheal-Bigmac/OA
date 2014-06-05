package com.oa.service.impl;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.oa.dao.UserDao;
import com.oa.model.Module;
import com.oa.model.Person;
import com.oa.model.Role;
import com.oa.model.UserPrivilege;
import com.oa.model.Users;
import com.oa.model.UsersRoles;
import com.oa.service.UserService;
@Component("userService")
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;

	public Users login(String hql, Object[] condition) {
		return userDao.login(hql, condition);
	}

	public UserDao getUserDao() {
		return userDao;
	}
  
	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void delete(Users user) {
		userDao.deleteUser(user);
	}

	public void update(Users user) {
		userDao.updateUser(user);
	}

	public Serializable addUser(Users user) {
		return userDao.addUser(user);
	}

	public Users exits(Serializable account) {
		return userDao.exits(account);
	}
	
	public List<Users> select() {
		String sql ="from User u";
		return userDao.select(sql);
	}

	public List<Person> getPageUsers(int i, Class<Person> clazz, String hql) {
		return userDao.getPageUsers(i, clazz, hql);
	}

	public List<Person> getAllUsers(Class<Person> clazz, String hql) {
		return userDao.getAllUsers(clazz, hql);
	}

	public Person getPersonId(Class<Person> clazz, Serializable id) {
		return userDao.getPersonId(clazz, id);
	}

	public Users getsUser(Person person) {
		String sql = "from Users u where u.personid = ?";
		return userDao.getsUser(sql, new Object[]{person});
	}

	public Person getsPerson(Class<Person> clazz, Serializable id) {
		return userDao.getPersonId(clazz, id);
	}

	public void deleteAccount(Users user) {
		userDao.deleteUser(user);
	}

	public List<Role> listRole() {
		String sql = "";
		return userDao.listRole(Role.class, sql);
	}

	public List<UsersRoles> getUsersRoles(Users user) {
		String sql = "and s.userId = "+user.getId();
		return userDao.getsUsersRoles(UsersRoles.class,sql);
	}

	public Users getThisUser(Class<Users> clazz, Serializable id) {
		return userDao.getThisUser(clazz, id);
	}

	public Role getThisRole(String name) {
		String sql = "from Role r where r.name = ?";
		return userDao.getThisRole(sql, new Object[]{name});
	}

	public void addUsersRoles(UsersRoles usersRoles) {
		userDao.addUsersRoles(usersRoles);
	}

	public void dealDeleteAccount(Integer id) {
		Person person = getsPerson(Person.class,id);
		Users user = getsUser(person);
		deleteAccount(user);
	}

	public UsersRoles getThisUsersRoles(int id) {
		UsersRoles usersRoles = userDao.getThisUsersRoles(UsersRoles.class, id);
		return usersRoles;
	}

	public void updateUsersRoles(UsersRoles usersRoles, String name) {
		String hql = "from Role r where r.name = ?";
		Role role = userDao.getThisRole(hql,new Object[]{name});
		usersRoles.setRoleId(role);
		userDao.updateUsersRoles(usersRoles);
	}

	public UsersRoles getUsersRolesById(Class<UsersRoles> clazz, Serializable id) {
		return userDao.getUsersRolesById(clazz, id);
	}

	public void deleteUsersRoles(UsersRoles usersRoles) {
		userDao.deleteUsersRols(usersRoles);
	}

	public Module getThisModule(Class<Module> class1,Serializable moduleId) {
		return userDao.getThisModule(class1, moduleId);
	}

	public void addUserPrivilege(UserPrivilege up) {
		userDao.addUserPrivilege(up);
	}

	public UserPrivilege getUserPrivilege(String str, Object[] objects) {
		return userDao.getUserPrivilege(str, objects);
	}

	

	public void updateUserPrivilege(UserPrivilege up) {
		userDao.updateUserPrivilege(up);
	}

	public void update(Person person) {
		userDao.updatePerson(person);
	}

	public Users getThisUser(Class<Users> clazz, Integer id) {
		return userDao.getThisUser(clazz, id);
	}
	public Users getUsersByAccount(String condition){
		String hql="from Users u where u.account=?";
		System.out.println(userDao);
		return userDao.getUser(hql, new Object[]{condition});
	}

}
