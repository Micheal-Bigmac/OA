package com.oa.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.oa.dao.impl.SuperDao;
import com.oa.model.PaymentPlan;
import com.oa.service.PaymentPlanService;
@Component("paymentPlanService")
public class PaymentPlanServiceImp implements PaymentPlanService {

	private SuperDao superDao;
	public Serializable addPaymentPlan(PaymentPlan PaymentPlan) {
		return superDao.add(PaymentPlan);
	}

	public void deletePaymentPlan(PaymentPlan PaymentPlan) {
		superDao.delete(PaymentPlan);
	}

	public PaymentPlan getPaymentPlan(Serializable id) {
		return (PaymentPlan) superDao.select(PaymentPlan.class, id);
	}

	public List<PaymentPlan> getAllPaymentPlans(Class clazz, String hql) {
		return objectToPaymentPlans(superDao.getAllObjects(clazz, hql));
	}
	private List<PaymentPlan> objectToPaymentPlans(List<Object> list){
		List<PaymentPlan> paymentPlans=new ArrayList<PaymentPlan>();
		for(Object o: list){
			paymentPlans.add((PaymentPlan)o);
		}
		return paymentPlans;
	}

	public List<PaymentPlan> getPagePaymentPlans(int index, Class clazz,
			String hql) {
		return objectToPaymentPlans(superDao.getPage(index, clazz, hql));
	}

	public void deletePaymentPlans(String[] ids) {
		superDao.deleteList(PaymentPlan.class, ids, "delete from PaymentPlan p where p.id");
	}

	@Resource
	public void setSuperDao(SuperDao superDao) {
		this.superDao = superDao;
	}

	public void updatePaymentPlans(PaymentPlan paymentPlan) {
		superDao.update(paymentPlan);
	}

}
