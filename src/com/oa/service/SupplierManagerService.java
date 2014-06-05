package com.oa.service;

import java.io.Serializable;
import java.util.List;

import com.oa.model.SupplierManager;

public interface SupplierManagerService {
	public Serializable addSupplierManager(SupplierManager SupplierManager);
	
	public void deleteSupplierManager(SupplierManager SupplierManager);
	
	public SupplierManager getSupplierManager(Serializable id);
	
	public List<SupplierManager> getAllSupplierManagers(Class clazz,String hql);
	
	public List<SupplierManager> getPageSupplierManagers(int index, Class clazz,String hql);
	
	public void deleteSupplierManagers(String []ids);
	
	public void updateSupplierManager(SupplierManager supplierManager);
}
