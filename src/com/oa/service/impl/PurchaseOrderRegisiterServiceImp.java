package com.oa.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.oa.dao.impl.SuperDao;
import com.oa.model.PurchaseOrderRegisiter;
import com.oa.service.PurchaseOrderRegisiterService;
@Component("purchaseOrderRegisiterService")
/**
 * 采购订单登记 Service
 * @author Big mac
 *
 */
public class PurchaseOrderRegisiterServiceImp implements PurchaseOrderRegisiterService {

	private SuperDao superDao;
	public Serializable addPurchaseOrderRegisiter(PurchaseOrderRegisiter PurchaseOrderRegisiter) {
		return superDao.add(PurchaseOrderRegisiter);
	}

	public void deletePurchaseOrderRegisiter(PurchaseOrderRegisiter PurchaseOrderRegisiter) {
		superDao.delete(PurchaseOrderRegisiter);
	}

	public PurchaseOrderRegisiter getPurchaseOrderRegisiter(Serializable id) {
		return (PurchaseOrderRegisiter) superDao.select(PurchaseOrderRegisiter.class, id);
	}

	public List<PurchaseOrderRegisiter> getAllPurchaseOrderRegisiters(Class clazz, String hql) {
		return ObjectToPurchaseOrderRegisiter(superDao.getAllObjects(clazz, hql));
	}
	private List<PurchaseOrderRegisiter> ObjectToPurchaseOrderRegisiter(List<Object> list){
		List<PurchaseOrderRegisiter> PurchaseOrderRegisiters=new ArrayList<PurchaseOrderRegisiter>();
		for(Object o: list){
			PurchaseOrderRegisiters.add((PurchaseOrderRegisiter)o);
		}
		return PurchaseOrderRegisiters;
	}

	public List<PurchaseOrderRegisiter> getPagePurchaseOrderRegisiters(int index, Class clazz, String hql) {
		return ObjectToPurchaseOrderRegisiter(superDao.getPage(index, clazz, hql));
	}

	public void deletePurchaseOrderRegisiters(String[] ids) {
		superDao.deleteList(PurchaseOrderRegisiter.class, ids, "delete from PurchaseOrderRegisiter r where r.id");
	}

	@Resource
	public void setSuperDao(SuperDao superDao) {
		this.superDao = superDao;
	}

	public void updatePurchaseOrderRegisiters(
			PurchaseOrderRegisiter purchaseOrderRegisiter) {
		superDao.update(purchaseOrderRegisiter);
	}

}
