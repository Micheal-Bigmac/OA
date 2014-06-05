package com.oa.service;

import java.io.Serializable;
import java.util.List;

import com.oa.model.PurchaseOrderRegisiter;

public interface PurchaseOrderRegisiterService {
	
	public Serializable addPurchaseOrderRegisiter(PurchaseOrderRegisiter PurchaseOrderRegisiter);
	
	public void deletePurchaseOrderRegisiter(PurchaseOrderRegisiter PurchaseOrderRegisiter);
	
	public PurchaseOrderRegisiter getPurchaseOrderRegisiter(Serializable id);
	
	public List<PurchaseOrderRegisiter> getAllPurchaseOrderRegisiters(Class clazz,String hql);
	
	public List<PurchaseOrderRegisiter> getPagePurchaseOrderRegisiters(int index, Class clazz,String hql);
	
	public void deletePurchaseOrderRegisiters(String []ids);
	
	public void updatePurchaseOrderRegisiters(PurchaseOrderRegisiter purchaseOrderRegisiter);
}
