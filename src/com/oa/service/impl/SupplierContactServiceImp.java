package com.oa.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.oa.dao.impl.SuperDao;
import com.oa.model.SupplierContact;
import com.oa.service.SupplierContactService;
@Component("supplierContactService")
/**
 * 供应商联系人
 * @author Big mac
 *
 */
public class SupplierContactServiceImp implements SupplierContactService {

	private SuperDao superDao;
	public Serializable addSupplierContact(SupplierContact SupplierContact) {
		return superDao.add(SupplierContact);
	}

	public void deleteSupplierContact(SupplierContact SupplierContact) {
		superDao.delete(SupplierContact);
	}

	public SupplierContact getSupplierContact(Serializable id) {
		return (SupplierContact) superDao.select(SupplierContact.class, id);
	}

	public List<SupplierContact> getAllSupplierContacts(Class clazz, String hql) {
		return ObjectToSupplierContact(superDao.getAllObjects(clazz, hql));
	}
	private List<SupplierContact> ObjectToSupplierContact(List<Object> list){
		List<SupplierContact> SupplierContacts=new ArrayList<SupplierContact>();
		for(Object o: list){
			SupplierContacts.add((SupplierContact)o);
		}
		return SupplierContacts;
	}

	public List<SupplierContact> getPageSupplierContacts(int index, Class clazz, String hql) {
		return ObjectToSupplierContact(superDao.getPage(index, clazz, hql));
	}

	public void deleteSupplierContacts(String[] ids) {
		superDao.deleteList(SupplierContact.class, ids, "delete from SupplierContact s where s.id");
	}

	@Resource
	public void setSuperDao(SuperDao superDao) {
		this.superDao = superDao;
	}

	public void updateSupplierContacts(SupplierContact supplierContact) {
		superDao.update(supplierContact);
	}

}
