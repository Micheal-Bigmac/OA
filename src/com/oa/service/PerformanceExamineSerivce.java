package com.oa.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.oa.model.ListPerformanceExamine;
import com.oa.model.Person;
import com.oa.model.PerformanceExamine;
import com.oa.model.PerformanceParameters;

public interface PerformanceExamineSerivce {

	void updatePerformanceExamine(PerformanceExamine performanceExamine);

	List<ListPerformanceExamine> getPerformanceExaminePages(int i,
			Class<ListPerformanceExamine> class1, String hql);

	int getAllPerformanceExamines(
			Class<ListPerformanceExamine> class1, String hql);

	void deletePerformanceExamine(PerformanceExamine pe);

	
	List<PerformanceParameters> getAllParams();
	List getAllParams(Serializable peId);

	List<Person> getAllPerson(Serializable peId);

	void addPerformanceExamine(PerformanceExamine performanceExamine);

	Serializable addListPerformanceExamine(String name,String instruction, String date, String recordUser);

	ListPerformanceExamine getLpe(Serializable peId);

	List<PerformanceExamine> selectPerformanceExamine(ListPerformanceExamine lpe);

	void deleteListPerformanceExamine(ListPerformanceExamine lpe);

	ListPerformanceExamine select(Serializable peId);

	Map<Integer, Map<String, List>> getAllData(Integer peId);

}
