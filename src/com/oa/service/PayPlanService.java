package com.oa.service;

import java.io.Serializable;
import java.util.List;

import com.oa.model.PayPlan;

public interface PayPlanService {
	public Serializable addPayPlan(PayPlan PayPlan);
	
	public void deletePayPlan(PayPlan PayPlan);
	
	public PayPlan getPayPlan(Serializable id);
	
	public List<PayPlan> getAllPayPlans(Class clazz,String hql);
	
	public List<PayPlan> getPagePayPlans(int index, Class clazz,String hql);
	
	public void deletePayPlans(String []ids);
	
	public void updatePayPlans(PayPlan payPlan);
}
