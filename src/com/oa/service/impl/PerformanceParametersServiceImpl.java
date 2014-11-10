package com.oa.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.oa.dao.SuperDaoInte;
import com.oa.model.PerformanceParameters;
import com.oa.service.PerformanceParametersService;
@Component("performanceParametersService")
public class PerformanceParametersServiceImpl implements PerformanceParametersService{

	private SuperDaoInte superDao;
	public void addPerformanceParameters(PerformanceParameters performanceParameters) {
		superDao.add(performanceParameters);
	}

	public void updatePerformanceParameters(PerformanceParameters performanceParameters) {
		superDao.update(performanceParameters);
	}

	public List<PerformanceParameters> getPerformanceParametersPages(int i,
			Class<PerformanceParameters> class1, String hql) {
		return convertToPerformanceParameters(superDao.getPage(i, class1, hql));
	}

	public List<PerformanceParameters> convertToPerformanceParameters(List<Object> objects) {
		List<PerformanceParameters> list = new ArrayList<PerformanceParameters>();
		for(int i=0; i<objects.size(); i++) {
			list.add((PerformanceParameters)objects.get(i));
		}
		return list;
	}
	
	public List<PerformanceParameters> getAllPerformanceParameterss(
			Class<PerformanceParameters> class1, String hql) {
		return convertToPerformanceParameters(superDao.getAllObjects(class1, hql));
	}

	public PerformanceParameters selectPerformanceParameters(
			Class<PerformanceParameters> class1, Serializable performanceParametersId) {
		return (PerformanceParameters)superDao.select(class1, performanceParametersId);
	}

	public void deletePerformanceParameters(PerformanceParameters performanceParameters) {
		superDao.delete(performanceParameters);
	}

	public SuperDaoInte getSuperDao() {
		return superDao;
	}
	@Resource
	public void setSuperDao(SuperDaoInte superDao) {
		this.superDao = superDao;
	}

	

}
