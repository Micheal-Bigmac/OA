package com.oa.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.oa.dao.impl.SuperDao;
import com.oa.model.SalesAgreement;
import com.oa.service.SalesAgreementService;
@Component("salesAgreementService")
public class SalesAgreeMentServiceImp implements SalesAgreementService {

	private SuperDao superDao;
	public Serializable addSalesAgreement(SalesAgreement sales) {
		return superDao.add(sales);
	}

	public void deleteSalesAgreement(SalesAgreement sales) {
		superDao.delete(sales);
	}

	public SalesAgreement getSalesAgreement(Serializable id) {
		return (SalesAgreement) superDao.select(SalesAgreement.class, id);
	}

	public List<SalesAgreement> getAllsalesAgreements(Class clazz, String hql) {
		return objectToSalesAgreements(superDao.getAllObjects(clazz, hql));
	}
	
	private List<SalesAgreement> objectToSalesAgreements(List<Object> list){
		List<SalesAgreement> salesAgreements=new ArrayList<SalesAgreement>();
		for(Object o: list){
			salesAgreements.add((SalesAgreement)o);
		}
		return salesAgreements;
	}

	public List<SalesAgreement> getpageAgreements(int index, Class clazz,
			String hql) {
		return objectToSalesAgreements(superDao.getPage(index, clazz, hql));
	}

	public void deleteSalesAgreements(String[] ids) {
		superDao.deleteList(SalesAgreementService.class, ids, "delete from SalesAgreement s where s.id ");
	}

	@Resource
	public void setSuperDao(SuperDao superDao) {
		this.superDao = superDao;
	}

	public void updateSalesAgreement(SalesAgreement sales) {
		superDao.update(sales);
	}

}
