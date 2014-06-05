package com.oa.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.oa.dao.impl.SuperDao;
import com.oa.model.PayPlan;
import com.oa.service.PayPlanService;
@Component("payPlanService")
public class PayPlanServiceImp implements PayPlanService {

	private SuperDao superDao;
	public Serializable addPayPlan(PayPlan PayPlan) {
		return superDao.add(PayPlan);
	}

	public void deletePayPlan(PayPlan PayPlan) {
		superDao.delete(PayPlan);
	}

	public PayPlan getPayPlan(Serializable id) {
		return (PayPlan) superDao.select(PayPlan.class, id);
	}

	public List<PayPlan> getAllPayPlans(Class clazz, String hql) {
		return ObjectToPayPlan(superDao.getAllObjects(clazz, hql));
	}
	private List<PayPlan> ObjectToPayPlan(List<Object> list){
		List<PayPlan> PayPlans=new ArrayList<PayPlan>();
		for(Object o: list){
			PayPlans.add((PayPlan)o);
		}
		return PayPlans;
	}

	public List<PayPlan> getPagePayPlans(int index, Class clazz, String hql) {
		return ObjectToPayPlan(superDao.getPage(index, clazz, hql));
	}

	public void deletePayPlans(String[] ids) {
		superDao.deleteList(PayPlan.class, ids, "delete from PayPlan p where p.id");
	}

	@Resource
	public void setSuperDao(SuperDao superDao) {
		this.superDao = superDao;
	}

	public void updatePayPlans(PayPlan payPlan) {
		superDao.update(payPlan);
	}

}
