package com.oa.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.oa.model.OnAndOffRegister;
import com.oa.model.UserOnAndOffRegister;

public interface AttendanceService {

	void addOnAndOffTimeSet(String registerOn, String registerOff);

	List<OnAndOffRegister> getRegisterSet();

	void add(UserOnAndOffRegister userOnAndOffRegister);

	List<UserOnAndOffRegister> select(Serializable id, String date);

	void update(UserOnAndOffRegister userOnAndOffRegister);

	UserOnAndOffRegister getUoor(Serializable id);

}
