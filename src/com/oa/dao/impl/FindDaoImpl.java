package com.oa.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.oa.dao.FindDao;
import com.oa.dao.SuperDaoInte;
import com.oa.model.ContractProductRecord;
import com.oa.model.OrderProductRecord;
import com.oa.model.PaymentPlan;
import com.oa.model.Product;

@Component("findDao")
public class FindDaoImpl implements FindDao {

	private SuperDaoInte superDao;
	public List findByCondition(int i, String clazz, String findCondition,
			String textfield, String hql) {
		return superDao.getPage(i, clazz, findCondition, textfield, hql);
	}

	public List getAllContents(String className, String hql) {
		return superDao.getAllObjects(className, hql);
	}

	public SuperDaoInte getSuperDao() {
		return superDao;
	}

	@Resource
	public void setSuperDao(SuperDaoInte superDao) {
		this.superDao = superDao;
	}

	public List findByCondition(Integer id) {
		return superDao.find("from Person p where p.id = "+id);
	}

	public List findByCondition(int i, String name, String hql) {
		return superDao.getPage(i, name, hql);
	}

	public List<Product> findByCondition(String hql) {
		return convertToListProduct(superDao.find(hql));
	}
	
	public List<Product> convertToListProduct(List<Object> objects) {
		List<Product> products = new ArrayList<Product>();
		for(int i=0; i<objects.size(); i++) {
			products.add((Product)objects.get(i));
		}
		return products;
	}

	public List<ContractProductRecord> findByConditions(String hql) {
		return convertToListCPR(superDao.find(hql));
	}
	
	public List<ContractProductRecord> convertToListCPR(List<Object> objects) {
		List<ContractProductRecord> cpr = new ArrayList<ContractProductRecord>();
		for(int i=0; i<objects.size(); i++) {
			cpr.add((ContractProductRecord)objects.get(i));
		}
		return cpr;
	}

	public List<PaymentPlan> findByConditionsPP(String hql) {
		return convertToListPP(superDao.find(hql));
	}

	private List<PaymentPlan> convertToListPP(List<Object> objects) {
		List<PaymentPlan> cpr = new ArrayList<PaymentPlan>();
		for(int i=0; i<objects.size(); i++) {
			cpr.add((PaymentPlan)objects.get(i));
		}
		return cpr;
	}

	private List<OrderProductRecord> convertToOPR(List<Object> objects) {
		List<OrderProductRecord> cpr = new ArrayList<OrderProductRecord>();
		for(int i=0; i<objects.size(); i++) {
			cpr.add((OrderProductRecord)objects.get(i));
		}
		return cpr;
	}
	
	public List<OrderProductRecord> findByConditionOPR(String hql) {
		return convertToOPR(superDao.find(hql));
	}
}
