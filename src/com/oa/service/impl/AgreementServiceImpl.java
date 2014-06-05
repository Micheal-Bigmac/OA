package com.oa.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import com.oa.dao.SuperDaoInte;
import com.oa.model.Agreement;
import com.oa.service.AgreementSerivce;

@Component("agreementService")
public class AgreementServiceImpl implements AgreementSerivce{

	private SuperDaoInte superDao;

	

	public List<Agreement> selectAgree() {
		return convertToAgreement(superDao.find("from Agreement a"));
	}

	private List<Agreement> convertToAgreement(List<Object> objects) {
		List<Agreement> list = new ArrayList<Agreement>();
		for(int i=0; i<objects.size(); i++) {
			list.add((Agreement)objects.get(i));
		}
		return list;
	}

	public SuperDaoInte getSuperDao() {
		return superDao;
	}
	@Resource
	public void setSuperDao(SuperDaoInte superDao) {
		this.superDao = superDao;
	}

	public void addAgree(Agreement agreement) {
		superDao.add(agreement);
	}

	public void updateAgree(Agreement agreement) {
		superDao.update(agreement);
	}

	public Agreement selectAgree(Class<Agreement> clazz, Serializable agreeId) {
		return (Agreement)superDao.select(clazz, agreeId);
	}

	public void deleteAgree(Agreement agree) {
		System.out.println("agree isd is  "+agree.getId());
		superDao.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("delete from Agreement a where a.id = "+agree.getId()).executeUpdate();
	}

	public List<Agreement> getAgreementPages(int i, Class<Agreement> clazz,
			String hql) {
		return convertToAgreement(superDao.getPage(i, clazz, hql));
	}

	public List<Agreement> getAllAgreements(Class<Agreement> clazz, String hql) {
		return convertToAgreement(superDao.getDistinctAllObject(clazz, hql));
	}

}
