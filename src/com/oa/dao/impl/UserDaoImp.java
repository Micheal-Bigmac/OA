package com.oa.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.oa.dao.SuperDaoInte;
import com.oa.dao.UserDao;
import com.oa.model.*;

@Component("userDao")
public class UserDaoImp implements UserDao {
	private SuperDaoInte superDao;

	public Users login(String hql, Object[] conditions) {
		return (Users) superDao.check(hql, conditions);
	}

	public SuperDaoInte getSuperDao() {
		return superDao;
	}

	@Resource
	public void setSuperDao(SuperDaoInte superDao) {
		this.superDao = superDao;
	}

	public Serializable addUser(Users user) {
		return superDao.add(user);
	}

	public void deleteUser(Users user) {
		superDao.delete(user);
	}

	public Users exits(Serializable account) {
		return (Users) superDao.select(Users.class, account);
	}

	public void updateUser(Users user) {
		superDao.update(user);
	}

	public List<Users> select(String sql) {
		return convert(superDao.find(sql));
	}
	
	public List<Users> convert(List<Object> objects) {
		List<Users> list = new ArrayList<Users>();
		for(int i=0; i<objects.size(); i++) {
			list.add((Users)objects.get(i));
		}
		return list;
	}

	public List<Person> converts(List<Object> objects) {
		List<Person> list = new ArrayList<Person>();
		for(int i=0; i<objects.size(); i++) {
			list.add((Person)objects.get(i));
		}
		return list;
	}
	
	public List<Role> convertsRole(List<Object> objects) {
		List<Role> list = new ArrayList<Role>();
		for(int i=0; i<objects.size(); i++) {
			list.add((Role)objects.get(i));
		}
		return list;
	}
	
	public List<Person> getPageUsers(int i, Class<Person> clazz, String hql) {
		return converts(superDao.getPage(i, clazz, hql));
	}

	public List<Person> getAllUsers(Class<Person> clazz, String hql) {
		return converts(superDao.getAllObjects(clazz, hql));
	}

	public Person getPersonId(Class<Person> clazz, Serializable id) {
		return (Person)superDao.select(clazz, id);
	}

	public Users getsUser(String sql, Object[] objects) {
		return (Users)superDao.check(sql, objects);
	}

	public List<Role> listRole(Class<Role> clazz, String hql) {
		return convertsRole(superDao.getAllObjects(clazz, hql));
	}

	public List<UsersRoles> getsUsersRoles(Class<UsersRoles> user,String sql) {
		return convertToUserRoles(superDao.getAllObjects(user, sql));
	}

	public Users getThisUser(Class<Users> clazz, Serializable id) {
		return (Users)superDao.select(clazz, id);
	}

	public Role getThisRole(String sql, Object[] objects) {
		return (Role)superDao.check(sql, objects);
	}

	public void addUsersRoles(UsersRoles usersRoles) {
		superDao.add(usersRoles);
	}

	public List<UsersRoles> convertToUserRoles(List<Object> objects) {
		List<UsersRoles> list = new ArrayList<UsersRoles>();
		for(int i=0; i<objects.size(); i++) {
			list.add((UsersRoles)objects.get(i));
		}
		return list;
	}

	public UsersRoles getThisUsersRoles(Class<UsersRoles> clazz, Serializable id) {
		return (UsersRoles)superDao.select(clazz, id);
	}

	public void updateUsersRoles(UsersRoles usersRoles) {
		superDao.update(usersRoles);
	}

	public UsersRoles getUsersRolesById(Class<UsersRoles> clazz, Serializable id) {
		return (UsersRoles)superDao.select(clazz, id);
	}

	public void deleteUsersRols(UsersRoles usersRoles) {
		superDao.delete(usersRoles);
	}

	public Module getThisModule(Class<Module> class1, Serializable moduleId) {
		return (Module)superDao.select(class1, moduleId);
	}

	public void addUserPrivilege(UserPrivilege up) {
		superDao.add(up);
	}

	public UserPrivilege getUserPrivilege(String str, Object[] objects) {
		return (UserPrivilege)superDao.check(str, objects);
	}

	public void deleteUser(UserPrivilege up) {
		superDao.delete(up);
	}

	public void updateUserPrivilege(UserPrivilege up) {
		superDao.update(up);
	}

	public void updatePerson(Person person) {
		superDao.update(person);
	}

	public Users getThisUser(Class<Users> clazz, Integer id) {
		return (Users)superDao.select(clazz, id);
	}
	public Users getUser(String hql,Object []condition){
		return (Users) superDao.check(hql, condition);
	}
}
