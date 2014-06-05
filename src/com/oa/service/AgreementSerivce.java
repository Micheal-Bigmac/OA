package com.oa.service;

import java.io.Serializable;
import java.util.List;

import com.oa.model.Agreement;

public interface AgreementSerivce {
	public void updateAgree(Agreement agreement); 
	public void deleteAgree(Agreement agree);
	public Agreement selectAgree(Class<Agreement> clazz, Serializable agreeIdStr);
	public List<Agreement> selectAgree();
	public void addAgree(Agreement agreement);
	public List<Agreement> getAgreementPages(int i, Class<Agreement> class1,
			String hql);
	public List<Agreement> getAllAgreements(Class<Agreement> clazz, String hql);
}
