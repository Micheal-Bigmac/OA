package com.oa.service;

import java.io.Serializable;
import java.util.List;

import com.oa.model.PaymentPlan;

public interface PaymentPlanService {
	
	public Serializable addPaymentPlan(PaymentPlan PaymentPlan);
	
	public void deletePaymentPlan(PaymentPlan PaymentPlan);
	
	public PaymentPlan getPaymentPlan(Serializable id);
	
	public List<PaymentPlan> getAllPaymentPlans(Class clazz,String hql);
	
	public List<PaymentPlan> getPagePaymentPlans(int index, Class clazz,String hql);
	
	public void deletePaymentPlans(String []ids);
	
	public void updatePaymentPlans(PaymentPlan paymentPlan);
}
