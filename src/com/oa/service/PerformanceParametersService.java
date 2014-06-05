package com.oa.service;

import java.io.Serializable;
import java.util.List;

import com.oa.model.PerformanceParameters;

public interface PerformanceParametersService {

	void addPerformanceParameters(PerformanceParameters performanceParameters);

	void updatePerformanceParameters(PerformanceParameters performanceParameters);

	List<PerformanceParameters> getPerformanceParametersPages(int i,
			Class<PerformanceParameters> class1, String hql);

	List<PerformanceParameters> getAllPerformanceParameterss(
			Class<PerformanceParameters> class1, String hql);

	PerformanceParameters selectPerformanceParameters(
			Class<PerformanceParameters> class1, Serializable performanceParametersId);

	void deletePerformanceParameters(PerformanceParameters performanceParameters);

}
