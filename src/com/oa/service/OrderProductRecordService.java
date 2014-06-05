package com.oa.service;

import java.io.Serializable;
import java.util.List;
import com.oa.model.OrderProductRecord;

public interface OrderProductRecordService {
	
	public Serializable addOrderProductRecord(OrderProductRecord OrderProductRecord);
	
	public void deleteOrderProductRecord(OrderProductRecord OrderProductRecord);
	
	public OrderProductRecord getOrderProductRecord(Serializable id);
	
	public List<OrderProductRecord> getAllOrderProductRecords(Class clazz,String hql);
	
	public List<OrderProductRecord> getPageOrderProductRecords(int index, Class clazz,String hql);
	
	public void deleteOrderProductRecords(String []ids);
	
	public void updateOrderProductRecord(OrderProductRecord orderProductRecord);
	
}
