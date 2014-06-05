package com.oa.service;

import java.io.Serializable;
import java.util.List;

import com.oa.model.SalesAgreement;

public interface SalesAgreementService {
	
	public Serializable addSalesAgreement(SalesAgreement sales);
	
	public void deleteSalesAgreement(SalesAgreement sales);
	
	public SalesAgreement getSalesAgreement(Serializable id);
	
	public List<SalesAgreement> getAllsalesAgreements(Class clazz,String hql);
	
	public List<SalesAgreement> getpageAgreements(int index,Class clazz,String hql);
	
	public void deleteSalesAgreements(String []ids);
	
	public void updateSalesAgreement(SalesAgreement sales);
	
}
