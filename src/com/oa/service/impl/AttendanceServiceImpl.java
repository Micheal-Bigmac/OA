package com.oa.service.impl;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.oa.dao.AttendanceDao;
import com.oa.model.OnAndOffRegister;
import com.oa.model.UserOnAndOffRegister;
import com.oa.service.AttendanceService;

@Component("attendanceService")
public class AttendanceServiceImpl implements AttendanceService{
	private AttendanceDao attendanceDao;

	public AttendanceDao getAttendanceDao() {
		return attendanceDao;
	}

	@Resource
	public void setAttendanceDao(AttendanceDao attendanceDao) {
		this.attendanceDao = attendanceDao;
	}
	
	public void addOnAndOffTimeSet(String registerOn, String registerOff) {
		if(attendanceDao.existsData()) {
			System.out.println("exists data");
			attendanceDao.delete();
		}
		OnAndOffRegister oor = new OnAndOffRegister();
		oor.setRegularTime(registerOn);
		oor.setState("上班");
		attendanceDao.addOnAndOffTimeSet(oor);
		OnAndOffRegister oor2 = new OnAndOffRegister();
		oor2.setRegularTime(registerOff);
		oor2.setState("下班");
		attendanceDao.addOnAndOffTimeSet(oor2);
	}

	public List<OnAndOffRegister> getRegisterSet() {
		List<OnAndOffRegister> list = attendanceDao.select("from OnAndOffRegister oor");
		return list;
	}

	public void add(UserOnAndOffRegister userOnAndOffRegister) {
		attendanceDao.add(userOnAndOffRegister);
	}

	public List<UserOnAndOffRegister> select(Serializable id, String date) {
		return attendanceDao.selects("from UserOnAndOffRegister uoor where uoor.userId = " + id +" and uoor.date = '" + date + "'");
	}

	public void update(UserOnAndOffRegister userOnAndOffRegister) {
		attendanceDao.update(userOnAndOffRegister);
	}

	public UserOnAndOffRegister getUoor(Serializable id) {
		return attendanceDao.getUoor(id);
	}

}
