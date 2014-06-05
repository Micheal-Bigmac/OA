package com.oa.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.oa.dao.impl.SuperDao;
import com.oa.model.SupplierManager;
import com.oa.service.SupplierManagerService;
@Component("supplierManagerService")
/**
 * 供应商管理
 * @author Big mac
 *
 */
public class SupplierManagerServiceImp implements SupplierManagerService {

	private SuperDao superDao;
	public Serializable addSupplierManager(SupplierManager SupplierManager) {
		return superDao.add(SupplierManager);
	}

	public void deleteSupplierManager(SupplierManager SupplierManager) {
		superDao.delete(SupplierManager);
	}

	public SupplierManager getSupplierManager(Serializable id) {
		return (SupplierManager) superDao.select(SupplierManager.class, id);
	}

	public List<SupplierManager> getAllSupplierManagers(Class clazz, String hql) {
		return objectToSupplierManagers(superDao.getAllObjects(clazz, hql));
	}
	private List<SupplierManager> objectToSupplierManagers(List<Object> list){
		List<SupplierManager> SupplierManagers=new ArrayList<SupplierManager>();
		for(Object o: list){
			SupplierManagers.add((SupplierManager)o);
		}
		return SupplierManagers;
	}

	public List<SupplierManager> getPageSupplierManagers(int index, Class clazz,
			String hql) {
		return objectToSupplierManagers(superDao.getPage(index, clazz, hql));
	}

	public void deleteSupplierManagers(String[] ids) {
		superDao.deleteList(SupplierManager.class, ids, "delete from SupplierManager s where s.id");
	}

	@Resource
	public void setSuperDao(SuperDao superDao) {
		this.superDao = superDao;
	}

	public void updateSupplierManager(SupplierManager supplierManager) {
		superDao.update(supplierManager);
	}

}
