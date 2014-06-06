package com.oa.service;

import java.util.List;

import com.oa.model.ContractProductRecord;
import com.oa.model.OrderProductRecord;
import com.oa.model.PaymentPlan;
import com.oa.model.Product;

public interface FindService {

	List findByCondition(int i, String clazz, String findCondition,
			String textfield, String hql);

	List getAllContents(String className, String hql);

	List findByCondition(Integer account);

	List findByCondition(int i, String name, String hql);

	List<Product> findByCondition(String hql);

	List<ContractProductRecord> findByConditions(String hql);

	List<PaymentPlan> findByConditionsPP(String hql);

	List<OrderProductRecord> findByConditionsOPR(String string);

}
