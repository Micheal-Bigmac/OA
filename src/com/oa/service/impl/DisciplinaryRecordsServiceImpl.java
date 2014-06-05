package com.oa.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.oa.dao.SuperDaoInte;
import com.oa.model.DisciplinaryRecords;
import com.oa.service.DisciplinaryRecordsSerivce;

@Component("disciplinaryRecordsService")
public class DisciplinaryRecordsServiceImpl implements DisciplinaryRecordsSerivce{

	private SuperDaoInte superDao;

	
	private List<DisciplinaryRecords> convertToDisciplinaryRecords(List<Object> objects) {
		List<DisciplinaryRecords> list = new ArrayList<DisciplinaryRecords>();
		for(int i=0; i<objects.size(); i++) {
			list.add((DisciplinaryRecords)objects.get(i));
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

	
	public List<DisciplinaryRecords> getAllDisciplinaryRecordss(Class<DisciplinaryRecords> clazz, String hql) {
		return convertToDisciplinaryRecords(superDao.getDistinctAllObject(clazz, hql));
	}

	public void addDis(DisciplinaryRecords disciplinaryRecords) {
		superDao.add(disciplinaryRecords);
	}

	public void updateDis(DisciplinaryRecords disciplinaryRecords) {
		superDao.update(disciplinaryRecords);
	}

	public List<DisciplinaryRecords> selectDis() {
		return convertToDisciplinaryRecords(superDao.find("from DisciplinaryRecords a"));
	}

	public DisciplinaryRecords selectDis(Class<DisciplinaryRecords> clazz,
			Integer disId) {
		return (DisciplinaryRecords)superDao.select(clazz, disId);
	}

	public void deleteDis(DisciplinaryRecords dis) {
		superDao.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("delete from DisciplinaryRecords a where a.id = "+dis.getId()).executeUpdate();
	}

	public List<DisciplinaryRecords> getDisciplinaryRecordsPages(int i,
			Class<DisciplinaryRecords> clazz, String hql) {
		return convertToDisciplinaryRecords(superDao.getPage(i, clazz, hql));
	}

	public List<DisciplinaryRecords> getAllDisciplinaryRecords(
			Class<DisciplinaryRecords> clazz, String hql) {
		return convertToDisciplinaryRecords(superDao.getAllObjects(clazz, hql));
	}

	
}
