package com.oa.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.oa.dao.FindDao;
import com.oa.model.ContractProductRecord;
import com.oa.model.OrderProductRecord;
import com.oa.model.PaymentPlan;
import com.oa.model.Product;
import com.oa.service.FindService;

@Component("findService")
public class FindServiceImpl implements FindService{

	private FindDao findDao;
	public List findByCondition(int i, String clazz, String findCondition,
			String textfield, String hql) {
		return findDao.findByCondition(i, clazz, findCondition, textfield, hql);
	}

	public List getAllContents(String className, String hql) {
		return findDao.getAllContents(className, hql);
	}

	public FindDao getFindDao() {
		return findDao;
	}
	@Resource
	public void setFindDao(FindDao findDao) {
		this.findDao = findDao;
	}

	public List findByCondition(Integer id) {
		return findDao.findByCondition(id);
	}

	public List findByCondition(int i, String name, String hql) {
		return findDao.findByCondition(i,name,hql);
	}

	public List<Product> findByCondition(String hql) {
		return findDao.findByCondition(hql);
	}

	public List<ContractProductRecord> findByConditions(String hql) {
		return findDao.findByConditions(hql);
	}

	public List<PaymentPlan> findByConditionsPP(String hql) {
		return findDao.findByConditionsPP(hql);
	}

	public List<OrderProductRecord> findByConditionsOPR(String hql) {
		return findDao.findByConditionOPR(hql);
	}

}
