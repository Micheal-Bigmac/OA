package com.oa.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.oa.dao.AttendanceDao;
import com.oa.dao.SuperDaoInte;
import com.oa.model.OnAndOffRegister;
import com.oa.model.UserOnAndOffRegister;
import com.sun.jndi.url.ldaps.ldapsURLContextFactory;
import com.sun.org.apache.commons.digester.rss.Image;

@Component("attendanceDao")
public class AttendanceDaoImpl implements AttendanceDao {

	private SuperDaoInte superDao;

	public SuperDaoInte getSuperDao() {
		return superDao;
	}
	@Resource
	public void setSuperDao(SuperDaoInte superDao) {
		this.superDao = superDao;
	}
	
	public void addOnAndOffTimeSet(OnAndOffRegister oor) {
		superDao.add(oor);
	}
	
	public List<OnAndOffRegister> convertToListOnAndOffRegister(List<Object> listObject) {
		List<OnAndOffRegister> list = new ArrayList<OnAndOffRegister>();
		for(int i=0; i<listObject.size(); i++) {
			list.add((OnAndOffRegister)listObject.get(i));
		}
		return list;
	}
	
	public boolean existsData() {
		List<OnAndOffRegister> oor = convertToListOnAndOffRegister(superDao.find("from OnAndOffRegister oor"));
		if(oor.size() > 0) {
			System.out.println("not null");
			return true;
		}
		System.out.println("null");
		return false;
	}
	
	public void delete() {
		List<OnAndOffRegister> oor = convertToListOnAndOffRegister(superDao.find("from OnAndOffRegister oor"));
		for(int i=0; i<oor.size(); i++) {
			superDao.delete(oor.get(i));
		}
	}
	
	public List<OnAndOffRegister> select(String str) {
		return convertToListOnAndOffRegister(superDao.find(str));
	}
	public void add(UserOnAndOffRegister userOnAndOffRegister) {
		superDao.add(userOnAndOffRegister);
	}
	public List<UserOnAndOffRegister> selects(String sql) {
		return convertToListUserOnAndOffRegister(superDao.find(sql));
	}
	public List<UserOnAndOffRegister> convertToListUserOnAndOffRegister(List<Object> listObject) {
		List<UserOnAndOffRegister> list = new ArrayList<UserOnAndOffRegister>();
		for(int i=0; i<listObject.size(); i++) {
			list.add((UserOnAndOffRegister)listObject.get(i));
		}
		return list;
	}
	public void update(UserOnAndOffRegister userOnAndOffRegister) {
		superDao.update(userOnAndOffRegister);
	}
	public UserOnAndOffRegister getUoor(Serializable id) {
		return (UserOnAndOffRegister)superDao.select(UserOnAndOffRegister.class, id);
	}
}
