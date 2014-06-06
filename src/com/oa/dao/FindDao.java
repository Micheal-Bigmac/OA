package com.oa.dao;

import java.util.List;

import com.oa.model.ContractProductRecord;
import com.oa.model.OrderProductRecord;
import com.oa.model.PaymentPlan;
import com.oa.model.Product;

public interface FindDao {
	public List findByCondition(int i, String clazz, String findCondition, String textfield, String hql);
	public List getAllContents(String className, String hql);
	public List findByCondition(Integer id);
	public List findByCondition(int i, String name, String hql);
	public List<Product> findByCondition(String hql);
	public List<ContractProductRecord> findByConditions(String hql);
	public List<PaymentPlan> findByConditionsPP(String hql);
	public List<OrderProductRecord> findByConditionOPR(String hql);
}
