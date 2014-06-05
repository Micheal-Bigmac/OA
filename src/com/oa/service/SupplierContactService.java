package com.oa.service;

import java.io.Serializable;
import java.util.List;

import com.oa.model.SupplierContact;

public interface SupplierContactService {
	public Serializable addSupplierContact(SupplierContact SupplierContact);
	
	public void deleteSupplierContact(SupplierContact SupplierContact);
	
	public SupplierContact getSupplierContact(Serializable id);
	
	public List<SupplierContact> getAllSupplierContacts(Class clazz,String hql);
	
	public List<SupplierContact> getPageSupplierContacts(int index, Class clazz,String hql);
	
	public void deleteSupplierContacts(String []ids);
	public void updateSupplierContacts(SupplierContact supplierContact);
	
	
}
