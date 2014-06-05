package com.oa.dao;

import java.io.Serializable;
import java.util.List;

import com.oa.model.OnAndOffRegister;
import com.oa.model.UserOnAndOffRegister;

public interface AttendanceDao {

	void addOnAndOffTimeSet(OnAndOffRegister oor);

	boolean existsData();

	void delete();

	List<OnAndOffRegister> select(String str);

	void add(UserOnAndOffRegister userOnAndOffRegister);

	List<UserOnAndOffRegister> selects(String string);

	void update(UserOnAndOffRegister userOnAndOffRegister);

	UserOnAndOffRegister getUoor(Serializable id);

}
